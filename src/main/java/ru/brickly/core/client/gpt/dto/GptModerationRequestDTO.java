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
        systemMessage.setContent("""
                Ты модератор пользовательских отзывов. Определи статус отзыва.
                
                Ответь только одним словом:
                OK
                TOXIC
                CENSORED
                SPAM
                
                Правила:
                
                TOXIC — отзыв содержит мат, замаскированный мат (е6лан, ху1ло) или оскорбления человека
                 (Оскорбления — это прямые унижения личности (идиот, дебил, тупой и т.п.).
                 Негативные характеристики продавца (безответственный, обманул, плохой продавец) разрешены.)
                
                CENSORED — отзыв позитивный или нейтральный, но содержит грубые или матерные слова, флирт, сексуальные или интимные фразы.
                
                SPAM — отзыв бессмысленный, не относится к товару или продавцу, содержит рекламу, набор символов, странные обвинения.
                
                OK — нормальный отзыв о товаре, доставке или продавце без мата и оскорблений. Негативная критика разрешена.
                
                Важно:
                
                * критика продавца или товара допустима
                * сарказм и эмоции допустимы
                * если есть мат → TOXIC
                * если текст не относится к покупке → SPAM
                * флирт, сексуальные или интимные фразы ЗАПРЕЩЕНЫ → CENSORED
                
                Ответь только одним словом.
                
                Отзыв:
                """);

        Message userMessage = new Message();
        userMessage.setRole("user");
        userMessage.setContent(text);

        this.messages = List.of(systemMessage, userMessage);
    }
}
