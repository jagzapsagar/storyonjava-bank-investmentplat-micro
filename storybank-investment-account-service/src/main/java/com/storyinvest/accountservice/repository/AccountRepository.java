package com.storyinvest.accountservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyinvest.accountservice.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	
    // fetch accounts for a specific user
    List<AccountEntity> findByUserId(Long userId);

    // find account by account number
    Optional<AccountEntity> findByAccountNumber(String accountNumber);

}
