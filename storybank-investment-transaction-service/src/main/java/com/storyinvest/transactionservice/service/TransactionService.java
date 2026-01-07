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

	//private final String ACCOUNT_SERVICE_URL = "http://localhost:8086/accounts/update-balance";

	
	public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
			AccountClient accountClient) {
		super();
		this.transactionRepository = transactionRepository;
		this.transactionMapper = transactionMapper;
		this.accountClient = accountClient;
	}

	/*
	 * //@Retry(name = "accountService", fallbackMethod = "accountFallback") public
	 * TransactionResponseDTO createTransaction(TransactionRequestDTO dto) { int
	 * temp=1; System.out.println("--- trying to call account service!!!!!"+temp);
	 * temp++; // 1️⃣ Step 1 – Hit Account Service → Get Account Details
	 * AccountResponseDTO account = restTemplate.getForObject(
	 * "http://STORYBANK-INVESTMENT-ACCOUNT-SERVICE/accounts/" + dto.getAccountId(),
	 * AccountResponseDTO.class);
	 * System.out.println("---------------------------------");
	 * System.out.println("---------------" + account + "------------------"); if
	 * (account == null) { throw new
	 * AccountNotFoundException("Account not found with ID: " + dto.getAccountId());
	 * }
	 * 
	 * // Step 2: Validate Transaction Type if
	 * (!dto.getTransactionType().equalsIgnoreCase("CREDIT") &&
	 * !dto.getTransactionType().equalsIgnoreCase("DEBIT")) { throw new
	 * InvalidTransactionTypeException("Invalid Transaction Type: " +
	 * dto.getTransactionType()); }
	 * 
	 * double currentBalance = account.getBalance(); double amount =
	 * dto.getAmount();
	 * 
	 * // Step 3: Check Balance for DEBIT if
	 * (dto.getTransactionType().equalsIgnoreCase("DEBIT")) { if
	 * (account.getBalance() < dto.getAmount()) { throw new
	 * InsufficientBalanceException("Insufficient balance for this transaction."); }
	 * }
	 * 
	 * // 3️⃣ Step 3 – Prepare RequestBody for Account Service BalanceUpdateRequest
	 * balanceReq = new BalanceUpdateRequest();
	 * balanceReq.setAccountId(dto.getAccountId());
	 * balanceReq.setAmount(dto.getAmount());
	 * balanceReq.setType(dto.getTransactionType()); // "DEBIT" or "CREDIT"
	 * 
	 * try { restTemplate.put(
	 * "http://STORYBANK-INVESTMENT-ACCOUNT-SERVICE/accounts/update-balance",
	 * balanceReq); accountClient.updateBalance(balanceReq); } catch
	 * (HttpClientErrorException.NotFound ex) { throw new
	 * AccountNotFoundException("Account does not exist: " + dto.getAccountId()); }
	 * catch (HttpClientErrorException.BadRequest ex) { throw new
	 * InsufficientBalanceException("Account rejected balance update"); } catch
	 * (Exception ex) { throw new RuntimeException("Account service failed: " +
	 * ex.getMessage()); }
	 * 
	 * 
	 * // 5️⃣ Step 5 – Create transaction locally TransactionEntity entity =
	 * transactionMapper.toEntity(dto); entity.setCreatedAt(LocalDateTime.now());
	 * entity.setUpdatedAt(LocalDateTime.now());
	 * 
	 * TransactionEntity saved = transactionRepository.save(entity); // 3. Set
	 * transaction date (very important) TransactionResponseDTO tdto =
	 * transactionMapper.toDTO(saved); tdto.setTransactionDate(LocalDateTime.now());
	 * 
	 * // 2. Publish Kafka Event TransactionEvent event = new TransactionEvent();
	 * event.setTransactionId(saved.getId());
	 * event.setAccountId(saved.getAccountId());
	 * event.setTransactionType(saved.getTransactionType());
	 * event.setAmount(saved.getAmount()); event.setCategory(saved.getCategory());
	 * event.setTransactionDate(saved.getCreatedAt());
	 * 
	 * transactionEventProducer.publishTransaction(event);
	 * 
	 * return tdto; }
	 */
	
	//
	@Transactional
	public TransactionResponseDTO createTransaction(TransactionRequestDTO dto) {

	    // 1️⃣ Get Account (retry handled internally)
	    AccountResponseDTO account = accountClient.getAccount(dto.getAccountId());

	    if (account == null) {
	        throw new AccountNotFoundException(
	            "Account not found with ID: " + dto.getAccountId()
	        );
	    }

	    // 2️⃣ Validate Transaction Type
	    if (!"CREDIT".equalsIgnoreCase(dto.getTransactionType()) &&
	        !"DEBIT".equalsIgnoreCase(dto.getTransactionType())) {
	        throw new InvalidTransactionTypeException(
	            "Invalid Transaction Type: " + dto.getTransactionType()
	        );
	    }

	    // 3️⃣ Balance check for DEBIT
	    if ("DEBIT".equalsIgnoreCase(dto.getTransactionType()) &&
	        account.getBalance() < dto.getAmount()) {
	        throw new InsufficientBalanceException("Insufficient balance");
	    }

	    // 4️⃣ Update balance (retry + fallback inside client)
	    BalanceUpdateRequest balanceReq = new BalanceUpdateRequest();
	    balanceReq.setAccountId(dto.getAccountId());
	    balanceReq.setAmount(dto.getAmount());
	    balanceReq.setType(dto.getTransactionType());

	    accountClient.updateBalance(balanceReq);

	    // 5️⃣ Save Transaction
	    TransactionEntity entity = transactionMapper.toEntity(dto);
	    entity.setCreatedAt(LocalDateTime.now());
	    entity.setUpdatedAt(LocalDateTime.now());

	    TransactionEntity saved = transactionRepository.save(entity);

	    // 6️⃣ Publish Kafka Event
	    TransactionEvent event = new TransactionEvent();
	    event.setTransactionId(saved.getId());
	    event.setAccountId(saved.getAccountId());
	    event.setTransactionType(saved.getTransactionType());
	    event.setAmount(saved.getAmount());
	    event.setCategory(saved.getCategory());
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
        //fallbackResponse.setAccountId(accountId);
        //fallbackResponse.setStatus("TEMPORARILY_UNAVAILABLE");
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
