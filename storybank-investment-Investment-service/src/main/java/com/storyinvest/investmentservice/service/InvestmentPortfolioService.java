package com.storyinvest.investmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyinvest.investmentservice.dto.TransactionEvent;
import com.storyinvest.investmentservice.entity.InvestmentPortfolio;
import com.storyinvest.investmentservice.repository.InvestmentPortfolioRepository;

@Service
public class InvestmentPortfolioService {

    @Autowired
    private InvestmentPortfolioRepository repository;

    public void processInvestment(TransactionEvent event) {

        InvestmentPortfolio portfolio = new InvestmentPortfolio();

        portfolio.setTransactionId(event.getTransactionId());
        portfolio.setAccountId(event.getAccountId());
        portfolio.setInvestedAmount(event.getAmount());
        portfolio.setTransactionType(event.getTransactionType());
        portfolio.setInvestmentType("DEFAULT"); // later dynamic
        portfolio.setInvestmentDate(event.getTransactionDate());

        repository.save(portfolio);

        System.out.println("✅ Investment saved for transaction: "
                + event.getTransactionId());
    }
}

