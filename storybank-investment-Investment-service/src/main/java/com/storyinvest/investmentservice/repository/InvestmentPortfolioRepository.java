package com.storyinvest.investmentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyinvest.investmentservice.entity.InvestmentPortfolio;

public interface InvestmentPortfolioRepository extends JpaRepository<InvestmentPortfolio, Long> {

    Optional<InvestmentPortfolio> findByUserId(Long userId);

}
