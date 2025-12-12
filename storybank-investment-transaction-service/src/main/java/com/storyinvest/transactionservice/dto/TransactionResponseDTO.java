package com.storyinvest.transactionservice.dto;

import java.time.LocalDateTime;

public class TransactionResponseDTO {
	
	private Long id;
    private Long accountId;
    private String transactionType;
    private Double amount;
    private String description;
    private LocalDateTime transactionDate;
	public TransactionResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionResponseDTO(Long id, Long accountId, String transactionType, Double amount, String description,
			LocalDateTime transactionDate) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.description = description;
		this.transactionDate = transactionDate;
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
		return "TransactionResponseDTO [id=" + id + ", accountId=" + accountId + ", transactionType=" + transactionType
				+ ", amount=" + amount + ", description=" + description + ", transactionDate=" + transactionDate + "]";
	}
    
    

}
