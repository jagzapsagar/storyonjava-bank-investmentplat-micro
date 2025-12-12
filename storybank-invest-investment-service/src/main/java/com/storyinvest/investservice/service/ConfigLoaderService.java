package com.storyinvest.investservice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.storyinvest.investservice.config.ConfigClient;
import com.fasterxml.jackson.core.type.TypeReference;


import java.util.HashMap;
import java.util.Map;

@Service
public class ConfigLoaderService {

    @Autowired
    private ConfigClient configClient;

    private Map<String, Object> configMap = new HashMap<>();

    @PostConstruct
    public void loadConfig() {
        try {
            String yamlString = configClient.getInvestmentConfig();

            if (yamlString == null || yamlString.isEmpty()) {
                throw new RuntimeException("Config service returned empty YAML");
            }

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            configMap = mapper.readValue(yamlString, new TypeReference<Map<String, Object>>() {});

            System.out.println("Loaded config: " + configMap);

        } catch (Exception e) {
            System.err.println("Failed to load config: " + e.getMessage());
            // avoid crashing the application; initialize empty config
            configMap = new HashMap<>();
        }
    }

    public Object get(String key) {
        return configMap.get(key);
    }
}

