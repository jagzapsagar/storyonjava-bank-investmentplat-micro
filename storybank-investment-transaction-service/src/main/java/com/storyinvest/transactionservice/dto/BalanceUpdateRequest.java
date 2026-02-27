package com.storyinvest.transactionservice.dto;

import com.storyinvest.transactionservice.enums.TransactionCategory;

import jakarta.validation.constraints.NotNull;

public class BalanceUpdateRequest {

	private Long accountId;
	private Double amount;
	private String type; // "DEBIT" or "CREDIT"
	private String category;
	
	public BalanceUpdateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BalanceUpdateRequest(Long accountId, Double amount, String type, @NotNull String category) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.type = type;
		this.category = category;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "BalanceUpdateRequest [accountId=" + accountId + ", amount=" + amount + ", type=" + type + ", category="
				+ category + "]";
	}
	
	
	

}
