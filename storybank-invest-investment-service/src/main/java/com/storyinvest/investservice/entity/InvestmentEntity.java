package com.storyinvest.investservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "investments")			
public class InvestmentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;          // which user invested

    private String investmentType; // SIP, FD, Mutual Fund, Stocks etc.

    private Double amount;         // invested amount

    private Double returnsAmount;  // current value or final value
    
    private String status; // ACTIVE, CLOSED, PENDING

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

	public InvestmentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvestmentEntity(Long id, Long userId, String investmentType, Double amount, Double returnsAmount,
			String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.investmentType = investmentType;
		this.amount = amount;
		this.returnsAmount = returnsAmount;
		this.status = status;
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

	public String getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getReturnsAmount() {
		return returnsAmount;
	}

	public void setReturnsAmount(Double returnsAmount) {
		this.returnsAmount = returnsAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "InvestmentEntity [id=" + id + ", userId=" + userId + ", investmentType=" + investmentType + ", amount="
				+ amount + ", returnsAmount=" + returnsAmount + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	
}
