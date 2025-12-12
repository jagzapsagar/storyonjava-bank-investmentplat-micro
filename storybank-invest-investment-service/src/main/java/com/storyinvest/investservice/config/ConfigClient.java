package com.storyinvest.investservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "config-client", url = "http://localhost:8080")
public interface ConfigClient {
	//http://localhost:8080/config/investment-service
    @GetMapping("/config/investment-service")
    String getInvestmentConfig();
}

