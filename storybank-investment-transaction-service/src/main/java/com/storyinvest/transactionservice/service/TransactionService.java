package com.storyinvest.transactionservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.storyinvest.transactionservice.dto.AccountResponseDTO;
import com.storyinvest.transactionservice.dto.BalanceUpdateRequest;
import com.storyinvest.transactionservice.dto.TransactionEvent;
import com.storyinvest.transactionservice.dto.TransactionRequestDTO;
import com.storyinvest.transactionservice.dto.TransactionResponseDTO;
import com.storyinvest.transactionservice.entity.TransactionEntity;
import com.storyinvest.transactionservice.enums.TransactionCategory;
import com.storyinvest.transactionservice.enums.TransactionStatus;
import com.storyinvest.transactionservice.enums.TransactionType;
import com.storyinvest.transactionservice.exception.AccountNotFoundException;
import com.storyinvest.transactionservice.exception.InsufficientBalanceException;
import com.storyinvest.transactionservice.exception.InvalidTransactionTypeException;
import com.storyinvest.transactionservice.feign.AccountServiceClient;
import com.storyinvest.transactionservice.mapper.TransactionMapper;
import com.storyinvest.transactionservice.repository.TransactionRepository;
import com.storyinvest.transactionservice.service.kafka.TransactionEventProducer;

import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	@Autowired
	private final TransactionRepository transactionRepository;
	@Autowired
	private final TransactionMapper transactionMapper;
	@Autowired
	private TransactionEventProducer transactionEventProducer;

	private final AccountClient accountClient;

	@Autowired
	private RestTemplate restTemplate;

	// private final String ACCOUNT_SERVICE_URL =
	// "http://localhost:8086/accounts/update-balance";

	public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
			AccountClient accountClient) {
		super();
		this.transactionRepository = transactionRepository;
		this.transactionMapper = transactionMapper;
		this.accountClient = accountClient;
	}

	//
	@Transactional
	public TransactionResponseDTO createTransaction(TransactionRequestDTO dto) {
		// 1️⃣ Validate category
		if (dto.getCategory() == null) {
			throw new InvalidTransactionTypeException("Transaction category is required");
		}

		// 1️⃣ Get Account (retry handled internally)
		AccountResponseDTO account = accountClient.getAccount(dto.getAccountId());

		if (account == null) {
			throw new AccountNotFoundException("Account not found with ID: " + dto.getAccountId());
		}

		// 3️⃣ Validate Transaction Type
		if (!dto.getTransactionType().equalsIgnoreCase(TransactionType.CREDIT.name())
		        && !dto.getTransactionType().equalsIgnoreCase(TransactionType.DEBIT.name())) {
			throw new InvalidTransactionTypeException("Invalid Transaction Type: " + dto.getTransactionType());
		}

		// 4️⃣ Balance check ONLY for DEBIT
		if (dto.getTransactionType() == TransactionType.DEBIT.name() && account.getBalance() < dto.getAmount()) {
			throw new InsufficientBalanceException("Insufficient balance");
		}

		// 4️⃣ Update balance synchronously (REST)
		BalanceUpdateRequest balanceReq = transactionMapper.toBalanceUpdateRequest(dto);

		accountClient.updateBalance(balanceReq);
		System.out.println("---------------------------");
		System.out.println(balanceReq);
		// 5️⃣ Save transaction
		TransactionEntity entity = transactionMapper.toEntity(dto);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());

		TransactionEntity saved = transactionRepository.save(entity);

		// 6️⃣ Publish Kafka event (AFTER success)
		TransactionEvent event = new TransactionEvent();
		event.setTransactionId(saved.getId());
		event.setAccountId(saved.getAccountId());
		event.setTransactionType(saved.getTransactionType().name());
		event.setAmount(saved.getAmount());
		event.setCategory(saved.getCategory().name());
		event.setTransactionDate(saved.getCreatedAt());

		transactionEventProducer.publishTransaction(event);

		// 7️⃣ Response
		TransactionResponseDTO response = transactionMapper.toDTO(saved);
		response.setTransactionDate(saved.getCreatedAt());

		return response;

	}

	// 🔁 Fallback Method
	public AccountResponseDTO accountFallback(TransactionRequestDTO dto, Exception ex) {

		System.out.println("-------- Fallback executed due to: " + ex.getMessage());

		AccountResponseDTO fallbackResponse = new AccountResponseDTO();
		// fallbackResponse.setAccountId(accountId);
		// fallbackResponse.setStatus("TEMPORARILY_UNAVAILABLE");
		fallbackResponse.setBalance(0.0);

		return fallbackResponse;
	}

	// Get Single Transaction
	public TransactionResponseDTO getTransactionById(Long id) {
		return transactionRepository.findById(id).map(transactionMapper::toDTO).orElse(null);
	}

	// Get All Transactions
	public List<TransactionResponseDTO> getAllTransactions() {
		return transactionRepository.findAll().stream().map(transactionMapper::toDTO).collect(Collectors.toList());
	}

	// Get Transaction by AccountId
	public List<TransactionResponseDTO> getTransactionsByAccountId(Long accountId) {
		return transactionRepository.findByAccountId(accountId).stream().map(transactionMapper::toDTO)
				.collect(Collectors.toList());
	}

	// Update Transaction
	public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO dto) {
		return transactionRepository.findById(id).map(existing -> {

			existing.setAccountId(dto.getAccountId());
			existing.setTransactionType(TransactionType.valueOf(dto.getTransactionType()));
			existing.setAmount(dto.getAmount());
			existing.setDescription(dto.getDescription());
			existing.setUpdatedAt(LocalDateTime.now());

			TransactionEntity updated = transactionRepository.save(existing);
			return transactionMapper.toDTO(updated);

		}).orElse(null);
	}

	// Delete Transaction
	public boolean deleteTransaction(Long id) {
		return transactionRepository.findById(id).map(existing -> {
			transactionRepository.delete(existing);
			return true;
		}).orElse(false);
	}

}
