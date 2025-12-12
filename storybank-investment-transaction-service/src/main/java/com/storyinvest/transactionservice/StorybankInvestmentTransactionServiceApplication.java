package com.storyinvest.transactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StorybankInvestmentTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorybankInvestmentTransactionServiceApplication.class, args);
	}

}
