package com.storyinvest.investservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
@EnableFeignClients(basePackages = "com.storyinvest.investservice")

public class StorybankInvestInvestmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorybankInvestInvestmentServiceApplication.class, args);
	}

}
