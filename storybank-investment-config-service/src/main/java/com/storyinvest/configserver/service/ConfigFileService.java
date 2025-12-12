package com.storyinvest.configserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConfigFileService {
	private final RestTemplate restTemplate = new RestTemplate();

    @Value("${config.git.repo-url}")
    private String repoUrl;

    public String loadConfig(String serviceName) {
        String url = repoUrl + "/" + serviceName + ".yml";

        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception ex) {
            return "Config not found for: " + serviceName;
        }
    }

}
