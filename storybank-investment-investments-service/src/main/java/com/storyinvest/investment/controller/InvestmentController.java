package com.storyinvest.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storyinvest.investment.dto.InvestmentUpdateRequest;
import com.storyinvest.investment.entity.InvestmentPortfolio;
import com.storyinvest.investment.service.InvestmentPortfolioService;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentPortfolioService service;

    // Get all investments for account
    @GetMapping("/account/{accountId}")
    public InvestmentPortfolio getByAccount(@PathVariable Long accountId) {
        return service.getInvestmentsByAccount(accountId);
    }

    // Get investment by transaction
    @GetMapping("/transaction/{transactionId}")
    public InvestmentPortfolio getByTransaction(@PathVariable Long transactionId) {
        return service.getInvestmentByTransaction(transactionId);
    }
    
    //Post Investment
    @PostMapping("/add")
    public ResponseEntity<InvestmentPortfolio> addInvestment(@RequestBody InvestmentUpdateRequest investmentUpdateRequest){
    	service.BalanceUpdateRequest(investmentUpdateRequest);
    	
    	return null;
    }
    
}
