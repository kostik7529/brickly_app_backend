package ru.brickly.core.client.gpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GptModerationRequestDTO {
    private String model;

    @JsonProperty("max_completion_tokens")
    private Integer maxTokens;

    private Double temperature;
    private List<Message> messages;

    @Data
    public static class Message {
        private String role;      // "system", "user", "assistant"
        private String content;
    }

    // Конструктор для модерации
    public GptModerationRequestDTO(String text) {

        Message systemMessage = new Message();
        systemMessage.setRole("system");
        systemMessage.setContent("Ты модератор контента. Проверь отзыв на токсичность или спам. Ответь только одним статусом в зависимости от содержания отзыва: 'SPAM', 'TOXIC', 'OK'");

        Message userMessage = new Message();
        userMessage.setRole("user");
        userMessage.setContent(text);

        this.messages = List.of(systemMessage, userMessage);
    }
}
