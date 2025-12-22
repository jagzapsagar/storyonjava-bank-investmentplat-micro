package com.storyinvest.investmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.storyinvest.investmentservice.dto.TransactionEvent;
import com.storyinvest.investmentservice.repository.InvestmentPortfolioRepository;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Service
public class KafkaConsumer {
	
	@Autowired
	private InvestmentPortfolioRepository repository;
	
	private final ObjectMapper objectMapper;

	public KafkaConsumer() {
		this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

	
	 @KafkaListener(topics = "transaction-created-topic", groupId = "notification-group")
	    public void handleUserCreatedEvent(String message) {
	        System.out.println("------📨 Received user created event: " + message);
	        
	        //
	        try {
	        	System.out.println("📨 Raw Kafka Message: " + message);

	            // 🔥 FIX: remove extra quotes if present
	            if (message.startsWith("\"")) {
	                message = objectMapper.readValue(message, String.class);
	            }

	            TransactionEvent event =
	                    objectMapper.readValue(message, TransactionEvent.class);

	            System.out.println("✅ Parsed Event: " + event);

	            if ("INVESTMENT".equalsIgnoreCase(event.getCategory())) {
	                System.out.println("💰 Investment transaction received");
	                // TODO: save to portfolio / update returns
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        	System.out.println("-------------Catch Exception");
	        }
	    }

	    

}
