package com.storyinvest.transactionservice.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.storyinvest.transactionservice.dto.TransactionEvent;

import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class TransactionEventProducer {

    private static final String TOPIC = "transaction-created-topic";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public TransactionEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendTransactionEvent(Object event) {
        kafkaTemplate.send("transaction-events", event);
    }

    public void publishTransaction(TransactionEvent event) {
    		
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
    	mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    	try {
			String eventJson = mapper.writeValueAsString(event);
			kafkaTemplate.send(TOPIC, eventJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    //	kafkaTemplate.send("transaction-created-topic", eventJson);
        
    }
}
