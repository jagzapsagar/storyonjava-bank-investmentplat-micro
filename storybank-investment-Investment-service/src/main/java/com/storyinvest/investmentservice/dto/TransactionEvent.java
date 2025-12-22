package com.storyinvest.investmentservice.dto;

import java.time.LocalDateTime;

public class TransactionEvent {
	private Long transactionId;
    private Long accountId;
    private String transactionType;   // CREDIT / DEBIT
    private Double amount;
    private String category;          // INVESTMENT / SALARY / BILL
    private String description;
    private LocalDateTime transactionDate;
	public TransactionEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionEvent(Long transactionId, Long accountId, String transactionType, Double amount, String category,
			String description, LocalDateTime transactionDate) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.category = category;
		this.description = description;
		this.transactionDate = transactionDate;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	@Override
	public String toString() {
		return "TransactionEvent [transactionId=" + transactionId + ", accountId=" + accountId + ", transactionType="
				+ transactionType + ", amount=" + amount + ", category=" + category + ", description=" + description
				+ ", transactionDate=" + transactionDate + "]";
	}
    
    
}
