package com.storyinvest.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StorybankInvestmentAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorybankInvestmentAccountServiceApplication.class, args);
	}

}
