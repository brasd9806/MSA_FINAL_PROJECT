package com.sparta.msa.ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OllamaConfig {

    @Value("${ollama.url}")
    private String ollamaUrl;

    @Value("${ollama.model}")
    private String ollamaModel;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getOllamaUrl() { return ollamaUrl; }
    public String getOllamaModel() { return ollamaModel; }
}
