package com.storyinvest.investment.dto;

public class InvestmentProcessedEvent {
	private Long transactionId;
	private Long userId;
	private Double amount;
	private String status; // SUCCESS | FAILED
	private String reason; // optional

	public InvestmentProcessedEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvestmentProcessedEvent(Long transactionId, Long userId, Double amount, String status, String reason) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.amount = amount;
		this.status = status;
		this.reason = reason;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "InvestmentProcessedEvent [transactionId=" + transactionId + ", userId=" + userId + ", amount=" + amount
				+ ", status=" + status + ", reason=" + reason + "]";
	}

}
