package com.storyinvest.transactionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyinvest.transactionservice.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

	// Get all transactions for a given accountId
    List<TransactionEntity> findByAccountId(Long accountId);

    // Get all transactions for a user
    //List<TransactionEntity> findByUserId(Long userId);
}
