package com.storyinvest.accountservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyinvest.accountservice.dto.AccountRequestDTO;
import com.storyinvest.accountservice.dto.AccountResponseDTO;
import com.storyinvest.accountservice.dto.BalanceUpdateRequest;
import com.storyinvest.accountservice.entity.AccountEntity;
import com.storyinvest.accountservice.exception.AccountNotFoundException;
import com.storyinvest.accountservice.mapper.AccountMapper;
import com.storyinvest.accountservice.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountMapper accountMapper;

	// Create Account
	public AccountResponseDTO createAccount(AccountRequestDTO dto) {
		AccountEntity entity = accountMapper.toEntity(dto);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());

		AccountEntity saved = accountRepository.save(entity);

		//// Generate 10-digit account number based on Bank, Branch, and sequential ID
		String bankCode = "X05428"; // Example bank code
		String branchCode = "11101"; // Example branch code

		long sequential = saved.getId(); // Use auto-increment ID
		String sequentialPart = String.format("%05d", sequential); // 5 digits for sequential

		String accountNumber = bankCode.substring(bankCode.length() - 3) // last 3 digits of bank code
				+ branchCode.substring(branchCode.length() - 2) // last 2 digits of branch code
				+ sequentialPart; // 5-digit sequential

		saved.setAccountNumber(accountNumber);

		// Save again with account number
		saved = accountRepository.save(saved);

		return accountMapper.toDTO(saved);
	}

	public AccountResponseDTO getAccountById(Long id) {
	    return accountRepository.findById(id)
	            .map(accountMapper::toDTO)
	            .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));
	}


	// Get All Accounts of a User
	public List<AccountResponseDTO> getAccountsByUserId(Long userId) {
		return accountRepository.findByUserId(userId).stream().map(accountMapper::toDTO).toList();
	}

	// Update Account
	public AccountResponseDTO updateAccount(Long id, AccountRequestDTO dto) {
		return accountRepository.findById(id).map(existing -> {

			existing.setUserId(dto.getUserId());
			existing.setAccountType(dto.getAccountType());
			existing.setBalance(dto.getBalance());

			// Italian documents
			existing.setCodiceFiscale(dto.getCodiceFiscale());
			existing.setCartaIdentita(dto.getCartaIdentita());
			existing.setPassportNumber(dto.getPassportNumber());

			// Personal info
			existing.setEmail(dto.getEmail());
			existing.setMobile(dto.getMobile());
			existing.setAddress(dto.getAddress());

			existing.setUpdatedAt(LocalDateTime.now());

			AccountEntity updated = accountRepository.save(existing);
			return accountMapper.toDTO(updated);

		}).orElse(null);
	}

	// Delete Account
	public boolean deleteAccount(Long id) {
		if (accountRepository.existsById(id)) {
			accountRepository.deleteById(id);
			return true;
		}
		return false;
	}

	// Get all accounts
	public List<AccountResponseDTO> getAllAccounts() {
		return accountRepository.findAll().stream().map(accountMapper::toDTO).collect(Collectors.toList());
	}

	public AccountResponseDTO updateBalance(BalanceUpdateRequest req) {

		return accountRepository.findById(req.getAccountId()).map(acc -> {

			double currentBalance = acc.getBalance();

			if (req.getType().equalsIgnoreCase("DEBIT")) {
				if (currentBalance < req.getAmount()) {
					throw new RuntimeException("Insufficient balance");
				}
				acc.setBalance(currentBalance - req.getAmount());
			} else if (req.getType().equalsIgnoreCase("CREDIT")) {
				acc.setBalance(currentBalance + req.getAmount());
			}

			acc.setUpdatedAt(LocalDateTime.now());
			return accountMapper.toDTO(accountRepository.save(acc));

		}).orElseThrow(() -> new RuntimeException("Account not found"));
	}

}
