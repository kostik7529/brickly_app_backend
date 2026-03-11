package ru.brickly.core.client.gpt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gpt.api")
@Data
public class GptModerationConfig {
    private String key;
    private String url = "https://api.openai.com/v1/chat/completions";
    private Integer timeout = 10000;
    private String model = "gpt-5-nano";
}
