package com.storyinvest.transactionservice.mapper;

import org.springframework.stereotype.Component;

import com.storyinvest.transactionservice.dto.TransactionRequestDTO;
import com.storyinvest.transactionservice.dto.TransactionResponseDTO;
import com.storyinvest.transactionservice.entity.TransactionEntity;

@Component
public class TransactionMapper {

	// Convert DTO → Entity
	public TransactionEntity toEntity(TransactionRequestDTO dto) {
		TransactionEntity entity = new TransactionEntity();

		entity.setAccountId(dto.getAccountId());
		entity.setTransactionType(dto.getTransactionType());
		entity.setAmount(dto.getAmount());
		entity.setDescription(dto.getDescription());
		entity.setCategory(dto.getCategory());


		return entity;
	}

	// Convert Entity → ResponseDTO
	public TransactionResponseDTO toDTO(TransactionEntity entity) {
		TransactionResponseDTO dto = new TransactionResponseDTO();

		dto.setId(entity.getId());
		dto.setAccountId(entity.getAccountId());
		dto.setTransactionType(entity.getTransactionType());
		dto.setAmount(entity.getAmount());
		dto.setDescription(entity.getDescription());
		dto.setCategory(entity.getCategory());


		return dto;
	}

}
