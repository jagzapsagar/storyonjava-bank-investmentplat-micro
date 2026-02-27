package com.storyinvest.investment.entity;

import java.time.LocalDateTime;

import com.storyinvest.investment.enums.InvestmentOperation;
import com.storyinvest.investment.enums.InvestmentStatus;
import com.storyinvest.investment.enums.InvestmentType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private Double amount;

    @Enumerated(EnumType.STRING)
    private InvestmentType investmentType;  // MUTUAL_FUND / STOCK / GOLD

    @Enumerated(EnumType.STRING)
    private InvestmentOperation operation; // INVEST / REDEEM

    @Enumerated(EnumType.STRING)
    private InvestmentStatus status; // PENDING / SUCCESS / FAILED

    private LocalDateTime investmentDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public InvestmentPortfolio() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvestmentPortfolio(Long id, Long transactionId, Long accountId, Double amount,
			InvestmentType investmentType, InvestmentOperation operation, InvestmentStatus status,
			LocalDateTime investmentDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.amount = amount;
		this.investmentType = investmentType;
		this.operation = operation;
		this.status = status;
		this.investmentDate = investmentDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public InvestmentType getInvestmentType() {
		return investmentType;
	}
	public void setInvestmentType(InvestmentType investmentType) {
		this.investmentType = investmentType;
	}
	public InvestmentOperation getOperation() {
		return operation;
	}
	public void setOperation(InvestmentOperation operation) {
		this.operation = operation;
	}
	public InvestmentStatus getStatus() {
		return status;
	}
	public void setStatus(InvestmentStatus status) {
		this.status = status;
	}
	public LocalDateTime getInvestmentDate() {
		return investmentDate;
	}
	public void setInvestmentDate(LocalDateTime investmentDate) {
		this.investmentDate = investmentDate;
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
		return "InvestmentPortfolio [id=" + id + ", transactionId=" + transactionId + ", accountId=" + accountId
				+ ", amount=" + amount + ", investmentType=" + investmentType + ", operation=" + operation + ", status="
				+ status + ", investmentDate=" + investmentDate + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

	
	
    

}
