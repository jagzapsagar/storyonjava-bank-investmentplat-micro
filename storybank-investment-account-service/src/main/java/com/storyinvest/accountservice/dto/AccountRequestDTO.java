package com.storyinvest.accountservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class AccountRequestDTO {
	
	@NotNull
    private Long userId;

    @NotBlank
    private String accountType;

    @PositiveOrZero
    private Double balance;

    // Italian identity fields
    private String codiceFiscale;
    private String cartaIdentita;
    private String passportNumber;

    // Personal details
    private String email;
    private String mobile;
    private String address;
	public AccountRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountRequestDTO(@NotNull Long userId, @NotBlank String accountType, @PositiveOrZero Double balance,
			String codiceFiscale, String cartaIdentita, String passportNumber, String email, String mobile,
			String address) {
		super();
		this.userId = userId;
		this.accountType = accountType;
		this.balance = balance;
		this.codiceFiscale = codiceFiscale;
		this.cartaIdentita = cartaIdentita;
		this.passportNumber = passportNumber;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "AccountRequestDTO [userId=" + userId + ", accountType=" + accountType + ", balance=" + balance
				+ ", codiceFiscale=" + codiceFiscale + ", cartaIdentita=" + cartaIdentita + ", passportNumber="
				+ passportNumber + ", email=" + email + ", mobile=" + mobile + ", address=" + address + "]";
	}
    
    
}
