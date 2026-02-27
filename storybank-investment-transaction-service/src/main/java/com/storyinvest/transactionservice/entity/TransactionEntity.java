package com.storyinvest.transactionservice.entity;

import java.time.LocalDateTime;

import com.storyinvest.transactionservice.enums.TransactionCategory;
import com.storyinvest.transactionservice.enums.TransactionStatus;
import com.storyinvest.transactionservice.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long accountId; // Account on which transaction occurred
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType; // CREDIT / DEBIT

	private Double amount; // Transaction amount

	// private String category; // INVESTMENT/ SALARY/ BILL_PAYMENT/ TRANSFER/
	// WITHDRAWAL
	@Enumerated(EnumType.STRING)
	private TransactionCategory category;

	@Enumerated(EnumType.STRING)
	private TransactionStatus status; // PENDING / SUCCESS / FAILED

	private String description; // Optional remark

	private Double closingBalance; // Balance after transaction

	private LocalDateTime transactionTime;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public TransactionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionEntity(Long id, Long accountId, TransactionType transactionType, Double amount,
			TransactionCategory category, TransactionStatus status, String description, Double closingBalance,
			LocalDateTime transactionTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.category = category;
		this.status = status;
		this.description = description;
		this.closingBalance = closingBalance;
		this.transactionTime = transactionTime;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionCategory getCategory() {
		return category;
	}

	public void setCategory(TransactionCategory category) {
		this.category = category;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "TransactionEntity [id=" + id + ", accountId=" + accountId + ", transactionType=" + transactionType
				+ ", amount=" + amount + ", category=" + category + ", status=" + status + ", description="
				+ description + ", closingBalance=" + closingBalance + ", transactionTime=" + transactionTime
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	


}
