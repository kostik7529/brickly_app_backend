package ru.brickly.core.client.gpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GptWebClientConfig {

    @Bean
    public WebClient gptWebClient(GptModerationConfig config) {
        return WebClient.builder()
                .baseUrl(config.getUrl())
                .defaultHeader("Authorization", "Bearer " + config.getKey())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}