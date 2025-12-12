package com.storyinvest.investservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyinvest.investservice.dto.InvestmentRequestDTO;
import com.storyinvest.investservice.dto.InvestmentResponseDTO;
import com.storyinvest.investservice.entity.InvestmentEntity;
import com.storyinvest.investservice.mapper.InvestmentMapper;
import com.storyinvest.investservice.repository.InvestmentRepository;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private InvestmentMapper investmentMapper;
    
    //Get ALL Investments
    public List<InvestmentResponseDTO> getAllInvestments() {
        return investmentRepository.findAll()
                .stream()
                .map(investmentMapper::toResponseDTO)
                .toList();
    }

    // Create Investment
    public InvestmentResponseDTO createInvestment(InvestmentRequestDTO requestDTO) {

        InvestmentEntity entity = investmentMapper.toEntity(requestDTO);
        
        double returns = 0.0;

        switch (entity.getInvestmentType().toLowerCase()) {
            case "mutual fund":
                returns = entity.getAmount() * 0.12;
                break;
            case "stocks":
                returns = entity.getAmount() * 0.15;
                break;
            case "fd":
                returns = entity.getAmount() * 0.07;
                break;
            default:
                returns = entity.getAmount() * 0.05; // default safe return
        }

        entity.setReturnsAmount(returns);

        InvestmentEntity saved = investmentRepository.save(entity);

        return investmentMapper.toResponseDTO(saved);
    }

    // Get Investment by ID
    public InvestmentResponseDTO getInvestmentById(Long id) {

        return investmentRepository.findById(id)
                .map(investmentMapper::toResponseDTO)
                .orElse(null);
    }

    // Get All Investments of a User
    public List<InvestmentResponseDTO> getInvestmentsByUserId(Long userId) {

        return investmentRepository.findByUserId(userId)
                .stream()
                .map(investmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Update Investment
    public InvestmentResponseDTO updateInvestment(Long id, InvestmentRequestDTO requestDTO) {

        return investmentRepository.findById(id).map(existing -> {

            existing.setUserId(requestDTO.getUserId());
            existing.setInvestmentType(requestDTO.getInvestmentType());
            existing.setAmount(requestDTO.getAmount());
            existing.setStatus(requestDTO.getStatus());
            existing.setUpdatedAt(LocalDateTime.now());

            InvestmentEntity updated = investmentRepository.save(existing);

            return investmentMapper.toResponseDTO(updated);

        }).orElse(null);
    }

    // Delete Investment
    public boolean deleteInvestment(Long id) {

        if (investmentRepository.existsById(id)) {
            investmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

