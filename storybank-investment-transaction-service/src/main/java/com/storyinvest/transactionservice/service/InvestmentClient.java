package com.storyinvest.transactionservice.service;

import org.springframework.web.client.RestTemplate;

import com.storyinvest.transactionservice.dto.AccountResponseDTO;
import com.storyinvest.transactionservice.dto.BalanceUpdateRequest;
import com.storyinvest.transactionservice.exception.AccountServiceUnavailableException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

public class InvestmentClient {
	
	public static final String INVESTMENTURI = "http://STORYBANK-INVESTMENT-INVESTMENTS-SERVICE";
	
	 private final RestTemplate restTemplate;
	    int temp=1;
	    public InvestmentClient(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

	    @Retry(name = "accountServiceRetry")
	    @CircuitBreaker(name = "accountServiceCB", fallbackMethod = "getAccountFallback")
	    public AccountResponseDTO getAccount(Long accountId) {
	    		
	    		System.out.println("---******------ "+temp++);
	        return restTemplate.getForObject(
	        		INVESTMENTURI+"/accounts/" + accountId,
	            AccountResponseDTO.class
	        );
	    }

	    public AccountResponseDTO getAccountFallback(Long accountId, Exception ex) {
	        throw new AccountServiceUnavailableException(
	            "---------- Account service is unavailable. Please try again later."
	        );
	    }

	    @Retry(name = "accountServiceRetry")
	    @CircuitBreaker(name = "accountServiceCB", fallbackMethod = "updateBalanceFallback")
	    public void updateBalance(BalanceUpdateRequest request) {

	        restTemplate.put(
	            "http://STORYBANK-INVESTMENT-ACCOUNT-SERVICE/accounts/update-balance",
	            request
	        );
	    }

	    public void updateBalanceFallback(BalanceUpdateRequest req, Exception ex) {
	        throw new AccountServiceUnavailableException(
	            "Unable to update account balance at this time."
	        );
	    }
}
