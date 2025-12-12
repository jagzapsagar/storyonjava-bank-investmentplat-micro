package com.storyinvest.accountservice.mapper;

import org.springframework.stereotype.Component;

import com.storyinvest.accountservice.dto.AccountRequestDTO;
import com.storyinvest.accountservice.dto.AccountResponseDTO;
import com.storyinvest.accountservice.entity.AccountEntity;

@Component
public class AccountMapper {
	
    public AccountEntity toEntity(AccountRequestDTO dto) {
        AccountEntity entity = new AccountEntity();

        entity.setUserId(dto.getUserId());
        entity.setAccountType(dto.getAccountType());
        entity.setBalance(dto.getBalance());

        entity.setCodiceFiscale(dto.getCodiceFiscale());
        entity.setCartaIdentita(dto.getCartaIdentita());
        entity.setPassportNumber(dto.getPassportNumber());

        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        entity.setAddress(dto.getAddress());

        return entity;
    }

    public AccountResponseDTO toDTO(AccountEntity entity) {
        AccountResponseDTO dto = new AccountResponseDTO();

        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setAccountType(entity.getAccountType());

        dto.setBalance(entity.getBalance());

        // Italian documents
        dto.setCodiceFiscale(entity.getCodiceFiscale());
        dto.setCartaIdentita(entity.getCartaIdentita());
        dto.setPassportNumber(entity.getPassportNumber());

        // Personal info
        dto.setEmail(entity.getEmail());
        dto.setMobile(entity.getMobile());
        dto.setAddress(entity.getAddress());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
