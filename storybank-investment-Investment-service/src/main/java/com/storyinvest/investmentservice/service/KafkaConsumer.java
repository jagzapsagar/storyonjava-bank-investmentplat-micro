package com.storyinvest.investmentservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	 @KafkaListener(topics = "transaction-created-topic", groupId = "notification-group")
	    public void handleUserCreatedEvent(String message) {
	        System.out.println("------📨 Received user created event: " + message);

	    }

}
