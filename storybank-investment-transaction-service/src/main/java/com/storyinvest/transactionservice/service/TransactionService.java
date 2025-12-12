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
import com.storyinvest.transactionservice.dto.TransactionRequestDTO;
import com.storyinvest.transactionservice.dto.TransactionResponseDTO;
import com.storyinvest.transactionservice.entity.TransactionEntity;
import com.storyinvest.transactionservice.exception.AccountNotFoundException;
import com.storyinvest.transactionservice.exception.InsufficientBalanceException;
import com.storyinvest.transactionservice.exception.InvalidTransactionTypeException;
import com.storyinvest.transactionservice.mapper.TransactionMapper;
import com.storyinvest.transactionservice.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private final TransactionRepository transactionRepository;
	@Autowired
	private final TransactionMapper transactionMapper;

	@Autowired
	private RestTemplate restTemplate;

	private final String ACCOUNT_SERVICE_URL = "http://localhost:8086/accounts/update-balance";

	public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
		super();
		this.transactionRepository = transactionRepository;
		this.transactionMapper = transactionMapper;
	}

	public TransactionResponseDTO createTransaction(TransactionRequestDTO dto) {

		// 1️⃣ Step 1 – Hit Account Service → Get Account Details
		AccountResponseDTO account = restTemplate.getForObject(
				"http://STORYBANK-INVESTMENT-ACCOUNT-SERVICE/accounts/" + dto.getAccountId(), AccountResponseDTO.class);
		System.out.println("---------------------------------");
		System.out.println("---------------" + account + "------------------");
		if (account == null) {
			throw new AccountNotFoundException("Account not found with ID: " + dto.getAccountId());
		}
		
		// Step 2: Validate Transaction Type
	    if (!dto.getTransactionType().equalsIgnoreCase("CREDIT") &&
	        !dto.getTransactionType().equalsIgnoreCase("DEBIT")) {
	        throw new InvalidTransactionTypeException("Invalid Transaction Type: " + dto.getTransactionType());
	    }

		double currentBalance = account.getBalance();
		double amount = dto.getAmount();

		// Step 3: Check Balance for DEBIT
	    if (dto.getTransactionType().equalsIgnoreCase("DEBIT")) {
	        if (account.getBalance() < dto.getAmount()) {
	            throw new InsufficientBalanceException("Insufficient balance for this transaction.");
	        }
	    }

		// 3️⃣ Step 3 – Prepare RequestBody for Account Service
		BalanceUpdateRequest balanceReq = new BalanceUpdateRequest();
		balanceReq.setAccountId(dto.getAccountId());
		balanceReq.setAmount(dto.getAmount());
		balanceReq.setType(dto.getTransactionType()); // "DEBIT" or "CREDIT"

		// 4️⃣ Step 4 – Call Account Service to update balance
		//restTemplate.put("http://STORYBANK-INVESTMENT-ACCOUNT-SERVICE/accounts/update-balance", balanceReq);
		
		try {
	        restTemplate.put(
	            "http://STORYBANK-INVESTMENT-ACCOUNT-SERVICE/accounts/update-balance",
	            balanceReq
	        );
	    } catch (HttpClientErrorException.NotFound ex) {
	        throw new AccountNotFoundException("Account does not exist: " + dto.getAccountId());
	    } catch (HttpClientErrorException.BadRequest ex) {
	        throw new InsufficientBalanceException("Account rejected balance update");
	    } catch (Exception ex) {
	        throw new RuntimeException("Account service failed: " + ex.getMessage());
	    }

		
		// 5️⃣ Step 5 – Create transaction locally
		TransactionEntity entity = transactionMapper.toEntity(dto);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());

		TransactionEntity saved = transactionRepository.save(entity);
		// 3. Set transaction date (very important)
		TransactionResponseDTO tdto = transactionMapper.toDTO(saved);
		tdto.setTransactionDate(LocalDateTime.now());
		return tdto;
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
			existing.setTransactionType(dto.getTransactionType());
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
