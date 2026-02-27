package com.storyinvest.transactionservice.dto;

import com.storyinvest.transactionservice.enums.TransactionCategory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TransactionRequestDTO {

	@NotNull
	private Long accountId;

	@NotNull
	@Pattern(regexp = "CREDIT|DEBIT")
	private String transactionType;

	@NotNull
	@Positive
	private Double amount;

	@NotNull
	private TransactionCategory category;

	@Size(max = 255)
	private String description;

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

	public TransactionCategory getCategory() {
		return category;
	}

	public void setCategory(TransactionCategory category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TransactionRequestDTO [accountId=" + accountId + ", transactionType=" + transactionType + ", amount="
				+ amount + ", category=" + category + ", description=" + description + "]";
	}

}
