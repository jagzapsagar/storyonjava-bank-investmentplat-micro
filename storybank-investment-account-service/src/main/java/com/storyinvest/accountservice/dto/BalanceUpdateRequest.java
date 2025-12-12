package com.storyinvest.accountservice.dto;

public class BalanceUpdateRequest {

	private Long accountId;
	private Double amount;
	private String type; // "DEBIT" or "CREDIT"
	public BalanceUpdateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BalanceUpdateRequest(Long accountId, Double amount, String type) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.type = type;
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
	@Override
	public String toString() {
		return "BalanceUpdateRequest [accountId=" + accountId + ", amount=" + amount + ", type=" + type + "]";
	}
	
	

}
