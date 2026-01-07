package com.storyinvest.investmentservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyinvest.investmentservice.dto.TransactionEvent;
import com.storyinvest.investmentservice.entity.InvestmentPortfolio;
import com.storyinvest.investmentservice.repository.InvestmentPortfolioRepository;

import jakarta.transaction.Transactional;

@Service
public class InvestmentPortfolioService {

	@Autowired
	private InvestmentPortfolioRepository repository;

	@Transactional
	public void processInvestment(TransactionEvent event) {

		if (!"INVESTMENT".equalsIgnoreCase(event.getCategory())) {
			return; // ignore non-investment
		}

		InvestmentPortfolio portfolio = repository.findByAccountId(event.getAccountId()).orElseGet(() -> {
			InvestmentPortfolio p = new InvestmentPortfolio();
			p.setAccountId(event.getAccountId());
			p.setInvestedAmount(0.0);
			return p;
		});

		if ("CREDIT".equalsIgnoreCase(event.getTransactionType())) {
			portfolio.setInvestedAmount(portfolio.getInvestedAmount() + event.getAmount());
		} else if ("DEBIT".equalsIgnoreCase(event.getTransactionType())) {
			portfolio.setInvestedAmount(portfolio.getInvestedAmount() - event.getAmount());
		}

		// portfolio.setLastUpdated(LocalDateTime.now());
		repository.save(portfolio);
	}

	// REST APIs
    public InvestmentPortfolio getInvestmentsByAccount(Long accountId) {
        return repository.findByAccountId(accountId).orElse(null);
    }

    public InvestmentPortfolio getInvestmentByTransaction(Long transactionId) {
        return repository.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Investment not found"));
    }
}
