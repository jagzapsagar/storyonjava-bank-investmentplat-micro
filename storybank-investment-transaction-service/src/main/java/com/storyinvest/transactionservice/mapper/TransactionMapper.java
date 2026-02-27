package com.storyinvest.transactionservice.mapper;

import org.springframework.stereotype.Component;

import com.storyinvest.transactionservice.dto.BalanceUpdateRequest;
import com.storyinvest.transactionservice.dto.TransactionRequestDTO;
import com.storyinvest.transactionservice.dto.TransactionResponseDTO;
import com.storyinvest.transactionservice.entity.TransactionEntity;
import com.storyinvest.transactionservice.enums.TransactionCategory;
import com.storyinvest.transactionservice.enums.TransactionType;

@Component
public class TransactionMapper {

	// Convert DTO → Entity
	public TransactionEntity toEntity(TransactionRequestDTO dto) {
		TransactionEntity entity = new TransactionEntity();

		entity.setAccountId(dto.getAccountId());
		entity.setTransactionType(TransactionType.valueOf(dto.getTransactionType()));
		entity.setAmount(dto.getAmount());
		entity.setCategory(dto.getCategory());
		entity.setDescription(dto.getDescription());
		return entity;
	}

	// Convert Entity → ResponseDTO
	public TransactionResponseDTO toDTO(TransactionEntity entity) {
		TransactionResponseDTO dto = new TransactionResponseDTO();

		dto.setId(entity.getId());
		dto.setAccountId(entity.getAccountId());
		dto.setTransactionType(entity.getTransactionType().name());
		dto.setAmount(entity.getAmount());
		dto.setDescription(entity.getDescription());
		dto.setCategory(entity.getCategory().name());

		return dto;
	}
	
	public BalanceUpdateRequest toBalanceUpdateRequest(TransactionRequestDTO dto) {
		BalanceUpdateRequest bRequest = new BalanceUpdateRequest();
		bRequest.setAccountId(dto.getAccountId());
		bRequest.setAmount(dto.getAmount());
		bRequest.setType(dto.getTransactionType());
		bRequest.setCategory(dto.getCategory().name());
		return bRequest;
	}

}
