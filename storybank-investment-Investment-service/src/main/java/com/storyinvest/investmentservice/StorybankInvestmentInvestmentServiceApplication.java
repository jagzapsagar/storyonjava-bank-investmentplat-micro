package com.storyinvest.investmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class StorybankInvestmentInvestmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorybankInvestmentInvestmentServiceApplication.class, args);
	}

}
