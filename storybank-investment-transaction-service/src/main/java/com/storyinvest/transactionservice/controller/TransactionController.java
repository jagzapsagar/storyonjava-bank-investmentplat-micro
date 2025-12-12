package com.storyinvest.transactionservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storyinvest.transactionservice.dto.TransactionRequestDTO;
import com.storyinvest.transactionservice.dto.TransactionResponseDTO;
import com.storyinvest.transactionservice.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	// ➤ Create Transaction
	@PostMapping
	public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO dto) {

		TransactionResponseDTO response = transactionService.createTransaction(dto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// ➤ Get Transaction by ID
	@GetMapping("/{id}")
	public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
		TransactionResponseDTO response = transactionService.getTransactionById(id);
		return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}

	// ➤ Get All Transactions
	@GetMapping
	public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
		List<TransactionResponseDTO> list = transactionService.getAllTransactions();
		return ResponseEntity.ok(list);
	}

	// ➤ Get Transactions by Account ID
	@GetMapping("/account/{accountId}")
	public ResponseEntity<List<TransactionResponseDTO>> getByAccountId(@PathVariable Long accountId) {
		List<TransactionResponseDTO> list = transactionService.getTransactionsByAccountId(accountId);
		return ResponseEntity.ok(list);
	}

}
