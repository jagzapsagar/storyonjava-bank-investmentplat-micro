package com.storyinvest.investment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyinvest.investment.entity.InvestmentPortfolio;

public interface InvestmentPortfolioRepository extends JpaRepository<InvestmentPortfolio, Long> {
	//Optional<InvestmentPortfolio> findByAccountId(Long accountId);
	//List<InvestmentPortfolio> findByTransactionId(Long transactionId);
	
	// One transaction → one investment
    Optional<InvestmentPortfolio> findByTransactionId(Long transactionId);

    Optional<InvestmentPortfolio> findByAccountId(Long accountId);

}
