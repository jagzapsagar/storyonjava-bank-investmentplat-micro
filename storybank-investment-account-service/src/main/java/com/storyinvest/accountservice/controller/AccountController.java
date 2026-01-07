package com.storyinvest.accountservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storyinvest.accountservice.dto.AccountRequestDTO;
import com.storyinvest.accountservice.dto.AccountResponseDTO;
import com.storyinvest.accountservice.dto.BalanceUpdateRequest;
import com.storyinvest.accountservice.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	// Create Account
	@PostMapping
	public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO dto) {
		AccountResponseDTO created = accountService.createAccount(dto);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	// Get Account by ID
	@GetMapping("/{id}")
	public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {

		AccountResponseDTO account = accountService.getAccountById(id);
		if (account != null) {
			return ResponseEntity.ok(account);
		}
		return ResponseEntity.notFound().build();

		// This is for testing Retry and CircuitBreaker of transaction service
		// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	// Get All Accounts
	@GetMapping
	public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
		List<AccountResponseDTO> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}

	// Update Account
	@PutMapping("/{id}")
	public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id, @RequestBody AccountRequestDTO dto) {
		AccountResponseDTO updated = accountService.updateAccount(id, dto);
		if (updated != null) {
			return ResponseEntity.ok(updated);
		}
		return ResponseEntity.notFound().build();
	}

	// Delete Account
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
		boolean deleted = accountService.deleteAccount(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/update-balance")
	public ResponseEntity<AccountResponseDTO> updateBalance(@RequestBody BalanceUpdateRequest req) {
		AccountResponseDTO updated = accountService.updateBalance(req);
		return ResponseEntity.ok(updated);
	}

}
