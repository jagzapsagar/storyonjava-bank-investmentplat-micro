package com.storyinvest.transactionservice.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.storyinvest.transactionservice.dto.AccountResponseDTO;
import com.storyinvest.transactionservice.dto.BalanceUpdateRequest;

@Component
public class AccountServiceFallbackFactory
        implements FallbackFactory<AccountServiceClient> {

    @Override
    public AccountServiceClient create(Throwable cause) {

        System.out.println("🔥 FEIGN FALLBACK TRIGGERED");
        System.out.println("CAUSE: " + cause.getClass().getName());

        return new AccountServiceClient() {

            @Override
            public AccountResponseDTO getAccount(Long id) {
                AccountResponseDTO dto = new AccountResponseDTO();
                dto.setAccountType("FALLBACK");
                dto.setBalance(0.0);
                return dto;
            }

            @Override
            public void updateBalance(BalanceUpdateRequest request) {
                System.out.println("🔥 updateBalance fallback executed");
            }
        };
    }
}

