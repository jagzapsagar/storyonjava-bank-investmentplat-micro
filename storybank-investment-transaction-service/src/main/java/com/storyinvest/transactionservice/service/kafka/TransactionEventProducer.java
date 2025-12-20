package com.storyinvest.transactionservice.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.storyinvest.transactionservice.dto.TransactionEvent;

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
        kafkaTemplate.send(TOPIC, event);
    }
}
