package com.storyinvest.transactionservice.dto;

public class AccountResponseDTO {
	private Long id;
    private Long userId;//
    private String accountNumber;
    private String accountType;
    private Double balance;
    //
    
    //
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "AccountResponseDTO [id=" + id + ", userId=" + userId + ", accountNumber=" + accountNumber
				+ ", accountType=" + accountType + ", balance=" + balance + "]";
	}
    
    
}
