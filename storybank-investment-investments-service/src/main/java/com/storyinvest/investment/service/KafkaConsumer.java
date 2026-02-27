package com.storyinvest.investment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.storyinvest.investment.dto.InvestmentProcessedEvent;
import com.storyinvest.investment.dto.TransactionEvent;
import com.storyinvest.investment.repository.InvestmentPortfolioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static com.storyinvest.investment.constant.StoryBankConstants.TRANSACTIONCREATEDTOPIC;
import static com.storyinvest.investment.constant.StoryBankConstants.NOTIFICATIONGROUP;

@Service
public class KafkaConsumer {

	@Autowired
	private InvestmentPortfolioRepository repository;
	@Autowired
	private InvestmentPortfolioService portfolioService;
	@Autowired
	private InvestmentEventProducer investmentEventProducer;

	private final ObjectMapper objectMapper;

	public KafkaConsumer() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new JavaTimeModule());
		this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	@KafkaListener(topics = TRANSACTIONCREATEDTOPIC, groupId = NOTIFICATIONGROUP)
	public void handleUserCreatedEvent(String message) {
		System.out.println("------📨 Received user created event: " + message);
		TransactionEvent event = new TransactionEvent();
		//
		try {
			System.out.println("📨 Raw Kafka Message: " + message);

			// 🔥 FIX: remove extra quotes if present
			if (message.startsWith("\"")) {
				message = objectMapper.readValue(message, String.class);
			}

			event = objectMapper.readValue(message, TransactionEvent.class);

			System.out.println("✅ Parsed Event: " + event);

			if (!"INVESTMENT".equalsIgnoreCase(event.getCategory())) {

				// TODO: save to portfolio / update returns
				return;
			}
			
			System.out.println("💰 Investment transaction received");
			portfolioService.processInvestment(event);
			
			// ✅ Publish SUCCESS event
	        InvestmentProcessedEvent processedEvent = new InvestmentProcessedEvent();
	        processedEvent.setTransactionId(event.getTransactionId());
	        processedEvent.setUserId(event.getAccountId());/////
	        processedEvent.setAmount(event.getAmount());
	        processedEvent.setStatus("SUCCESS");
	        processedEvent.setReason("Dummy from succsess");
	        

	        investmentEventProducer.publishInvestmentResult(processedEvent);

		} catch (Exception e) {
			
			// ❌ Publish FAILED event (compensation trigger)
	        InvestmentProcessedEvent failedEvent = new InvestmentProcessedEvent();
	        failedEvent.setTransactionId(event.getTransactionId());
	        failedEvent.setUserId(event.getAccountId());
	        failedEvent.setAmount(event.getAmount());
	        failedEvent.setStatus("FAILED");
	        failedEvent.setReason(e.getMessage());

	        investmentEventProducer.publishInvestmentResult(failedEvent);
			e.printStackTrace();
			System.err.println("❌ Failed to process Kafka message: " + message);
		}
	}

}
