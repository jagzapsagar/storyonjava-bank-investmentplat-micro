package com.storyinvest.configserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storyinvest.configserver.service.ConfigFileService;

@RestController
@RequestMapping("/config")
public class ConfigController {
	
	@Autowired
    private ConfigFileService configFileService;

    @GetMapping("/{serviceName}")
    public String getConfig(@PathVariable String serviceName) {
        return configFileService.loadConfig(serviceName);
    }

}
