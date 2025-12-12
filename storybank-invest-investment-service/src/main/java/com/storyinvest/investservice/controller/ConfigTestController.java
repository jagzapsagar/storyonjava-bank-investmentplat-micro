package com.storyinvest.investservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storyinvest.investservice.config.ConfigClient;

@RestController
@RequestMapping("/test")
public class ConfigTestController {

    @Autowired
    private ConfigClient configClient;

    @GetMapping("/raw-config")
    public String getRawConfig() {
        return configClient.getInvestmentConfig();
    }
}

