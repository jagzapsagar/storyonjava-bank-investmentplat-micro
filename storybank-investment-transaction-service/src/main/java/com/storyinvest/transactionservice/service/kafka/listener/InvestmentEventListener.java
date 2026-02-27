package com.storyinvest.transactionservice.service.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.storyinvest.transactionservice.dto.InvestmentProcessedEvent;

@Component
public class InvestmentEventListener {
	
	private final ObjectMapper objectMapper;

    public InvestmentEventListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
	
	@KafkaListener(topics = "investment-processed-topic", groupId = "transaction-group")
	public void handleInvestmentResult(String message) {
		try {
            InvestmentProcessedEvent event =
                objectMapper.readValue(message, InvestmentProcessedEvent.class);

            System.out.println("✅ Event parsed: " + event);

        } catch (Exception e) {
            e.printStackTrace();
        }
		System.out.println("---------------------------------");
	    System.out.println("📨 Investment Result Received: " + message);

	    //if ("SUCCESS".equalsIgnoreCase(event.getStatus())) {
	    	if ("SUCCESS".equalsIgnoreCase("jnnd")) {
	        //transactionService.markTransactionSuccess(event.getTransactionId());
	    } else {
	        //transactionService.markTransactionFailed(
	         //       event.getTransactionId(),
	          //      event.getReason()
	        //);
	    }
	}

}
