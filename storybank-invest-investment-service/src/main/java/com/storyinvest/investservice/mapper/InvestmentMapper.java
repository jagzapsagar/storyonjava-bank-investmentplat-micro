package com.storyinvest.investservice.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.storyinvest.investservice.dto.InvestmentRequestDTO;
import com.storyinvest.investservice.dto.InvestmentResponseDTO;
import com.storyinvest.investservice.entity.InvestmentEntity;
@Component
public class InvestmentMapper {
	// Request DTO -> Entity
    public InvestmentEntity toEntity(InvestmentRequestDTO request) {

        InvestmentEntity entity = new InvestmentEntity();

        entity.setUserId(request.getUserId());
        entity.setInvestmentType(request.getInvestmentType());
        entity.setAmount(request.getAmount());
        entity.setStatus(
                request.getStatus() != null ? request.getStatus() : "ACTIVE"
        );
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        return entity;
    }

    // Entity -> Response DTO
    public InvestmentResponseDTO toResponseDTO(InvestmentEntity entity) {

        InvestmentResponseDTO dto = new InvestmentResponseDTO();

        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setInvestmentType(entity.getInvestmentType());
        dto.setAmount(entity.getAmount());
        dto.setReturnsAmount(entity.getReturnsAmount());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
