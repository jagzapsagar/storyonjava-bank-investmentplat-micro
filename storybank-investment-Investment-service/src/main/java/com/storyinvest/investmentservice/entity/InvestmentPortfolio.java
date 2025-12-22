package com.storyinvest.investmentservice.entity;

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

    private Long userId;

    private Double totalInvestedAmount;

    private Double currentValue;

    private Double totalReturns;

    private LocalDateTime lastUpdated;

	public InvestmentPortfolio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvestmentPortfolio(Long id, Long userId, Double totalInvestedAmount, Double currentValue,
			Double totalReturns, LocalDateTime lastUpdated) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalInvestedAmount = totalInvestedAmount;
		this.currentValue = currentValue;
		this.totalReturns = totalReturns;
		this.lastUpdated = lastUpdated;
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

	public Double getTotalInvestedAmount() {
		return totalInvestedAmount;
	}

	public void setTotalInvestedAmount(Double totalInvestedAmount) {
		this.totalInvestedAmount = totalInvestedAmount;
	}

	public Double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}

	public Double getTotalReturns() {
		return totalReturns;
	}

	public void setTotalReturns(Double totalReturns) {
		this.totalReturns = totalReturns;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "InvestmentPortfolio [id=" + id + ", userId=" + userId + ", totalInvestedAmount=" + totalInvestedAmount
				+ ", currentValue=" + currentValue + ", totalReturns=" + totalReturns + ", lastUpdated=" + lastUpdated
				+ "]";
	}
    
    

}
