package com.storyinvest.investservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storyinvest.investservice.dto.InvestmentRequestDTO;
import com.storyinvest.investservice.dto.InvestmentResponseDTO;
import com.storyinvest.investservice.service.ConfigLoaderService;
import com.storyinvest.investservice.service.InvestmentService;

@RestController
//@RequestMapping("/investment")
@RequestMapping("/api/investments")
public class InvestmentController {
	
	@Autowired
    private InvestmentService investmentService;

    @Autowired
    private ConfigLoaderService configLoaderService;

	/*
	 * @GetMapping("/min-amount") public Object getMinAmount() { Map<String, Object>
	 * investment = (Map<String, Object>) configLoaderService.get("investment");
	 * return investment.get("minAmount");
	 * 
	 * 
	 * }
	 */
    
    //GET ALL INVESTMENTS
    @GetMapping
    public ResponseEntity<List<InvestmentResponseDTO>> getAll() {
        return ResponseEntity.ok(investmentService.getAllInvestments());
    }
    
    // Create Investment
    @PostMapping
    public ResponseEntity<InvestmentResponseDTO> createInvestment(
            @RequestBody InvestmentRequestDTO requestDTO) {

        InvestmentResponseDTO response = investmentService.createInvestment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get Investment by ID
    @GetMapping("/{id}")
    public ResponseEntity<InvestmentResponseDTO> getInvestmentById(@PathVariable Long id) {

        InvestmentResponseDTO response = investmentService.getInvestmentById(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    // Get all investments of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvestmentResponseDTO>> getInvestmentsByUserId(@PathVariable Long userId) {

        List<InvestmentResponseDTO> list = investmentService.getInvestmentsByUserId(userId);

        return ResponseEntity.ok(list);
    }

    // Update Investment
    @PutMapping("/{id}")
    public ResponseEntity<InvestmentResponseDTO> updateInvestment(
            @PathVariable Long id,
            @RequestBody InvestmentRequestDTO requestDTO) {

        InvestmentResponseDTO response = investmentService.updateInvestment(id, requestDTO);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    // Delete Investment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {

        boolean deleted = investmentService.deleteInvestment(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}

