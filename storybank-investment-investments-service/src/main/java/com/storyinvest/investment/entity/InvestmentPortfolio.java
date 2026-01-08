package com.storyinvest.investment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "investment_portfolio")
public class InvestmentPortfolio {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long transactionId;
	    private Long accountId;

	    private Double investedAmount;

	    private String investmentType;   // MUTUAL_FUND / STOCK / GOLD
	    private String transactionType;  // CREDIT / DEBIT

	    private LocalDateTime investmentDate;

	public InvestmentPortfolio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvestmentPortfolio(Long id, Long transactionId, Long accountId, Double investedAmount,
			String investmentType, String transactionType, LocalDateTime investmentDate) {
		super();
		this.id = id;
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.investedAmount = investedAmount;
		this.investmentType = investmentType;
		this.transactionType = transactionType;
		this.investmentDate = investmentDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(Double investedAmount) {
		this.investedAmount = investedAmount;
	}

	public String getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDateTime getInvestmentDate() {
		return investmentDate;
	}

	public void setInvestmentDate(LocalDateTime investmentDate) {
		this.investmentDate = investmentDate;
	}

	@Override
	public String toString() {
		return "InvestmentPortfolio [id=" + id + ", transactionId=" + transactionId + ", accountId=" + accountId
				+ ", investedAmount=" + investedAmount + ", investmentType=" + investmentType + ", transactionType="
				+ transactionType + ", investmentDate=" + investmentDate + "]";
	}

	
    

}
