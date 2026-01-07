package com.storyinvest.transactionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.storyinvest.transactionservice.dto.AccountResponseDTO;
import com.storyinvest.transactionservice.dto.BalanceUpdateRequest;

@FeignClient(
	    name = "STORYBANK-INVESTMENT-ACCOUNT-SERVICE",
	    		fallbackFactory = AccountServiceFallbackFactory.class
	)
public interface AccountServiceClient {
	@GetMapping("/accounts/{id}")
    AccountResponseDTO getAccount(@PathVariable Long id);
	
	@PutMapping("/accounts/update-balance")
	void updateBalance(@RequestBody BalanceUpdateRequest request);

}
