package com.storyinvest.accountservice.dto;

import java.time.LocalDateTime;

public class AccountResponseDTO {
	
	private Long id;
    private Long userId;
    private String accountNumber;
    private String accountType;

    private Double balance;

    // Italian documents
    private String codiceFiscale;
    private String cartaIdentita;
    private String passportNumber;

    // Personal info
    private String email;
    private String mobile;
    private String address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public AccountResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountResponseDTO(Long id, Long userId, String accountNumber, String accountType, Double balance,
			String codiceFiscale, String cartaIdentita, String passportNumber, String email, String mobile,
			String address, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.codiceFiscale = codiceFiscale;
		this.cartaIdentita = cartaIdentita;
		this.passportNumber = passportNumber;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
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
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCartaIdentita() {
		return cartaIdentita;
	}
	public void setCartaIdentita(String cartaIdentita) {
		this.cartaIdentita = cartaIdentita;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
		return "AccountResponseDTO [id=" + id + ", userId=" + userId + ", accountNumber=" + accountNumber
				+ ", accountType=" + accountType + ", balance=" + balance + ", codiceFiscale=" + codiceFiscale
				+ ", cartaIdentita=" + cartaIdentita + ", passportNumber=" + passportNumber + ", email=" + email
				+ ", mobile=" + mobile + ", address=" + address + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
    
    

}
