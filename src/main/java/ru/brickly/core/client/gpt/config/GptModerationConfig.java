package ru.brickly.core.client.gpt.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class GptModerationConfig {
    @Value("${gpt.api.key}")
    private String key;

    @Value("${gpt.api.url}")
    private String url;

    @Value("${gpt.api.timeout}")
    private Integer timeout;

    @Value("${gpt.api.model}")
    private String model;
}
