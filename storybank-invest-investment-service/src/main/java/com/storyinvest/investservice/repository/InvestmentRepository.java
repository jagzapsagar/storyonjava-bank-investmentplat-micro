package com.storyinvest.investservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyinvest.investservice.entity.InvestmentEntity;

public interface InvestmentRepository extends JpaRepository<InvestmentEntity, Long> {

	List<InvestmentEntity> findByUserId(Long userId);

}
