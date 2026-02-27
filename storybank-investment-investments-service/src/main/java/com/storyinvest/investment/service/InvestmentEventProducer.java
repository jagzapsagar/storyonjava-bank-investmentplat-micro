package com.storyinvest.investment.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//import com.storyinvest.investment.constant.StoryBankConstants;
import com.storyinvest.investment.dto.InvestmentProcessedEvent;
import static com.storyinvest.investment.constant.StoryBankConstants.INVESTMENTPROCESSEDTOPIC;


@Service
public class InvestmentEventProducer {
	//private static final String TOPIC = "investment-processed-topic";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InvestmentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishInvestmentResult(InvestmentProcessedEvent event) {
        kafkaTemplate.send(INVESTMENTPROCESSEDTOPIC, event.getTransactionId().toString(), event);
        System.out.println("📤 Published InvestmentProcessedEvent: " + event);
    }
}
