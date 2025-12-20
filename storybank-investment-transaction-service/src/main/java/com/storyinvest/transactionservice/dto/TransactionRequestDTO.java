package com.storyinvest.transactionservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransactionRequestDTO {
	
		@NotNull
	    private Long accountId;   // Foreign key (from Account Service)

	    @NotNull
	    private String transactionType;  // CREDIT / DEBIT

	    @Positive
	    private Double amount;
	    
	    private String category; 

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

		@Override
		public String toString() {
			return "TransactionRequestDTO [accountId=" + accountId + ", transactionType=" + transactionType
					+ ", amount=" + amount + ", category=" + category + ", description=" + description + "]";
		}

	    
}
