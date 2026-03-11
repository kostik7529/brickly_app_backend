package ru.brickly.core.client.gpt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.brickly.core.client.gpt.config.GptModerationConfig;
import ru.brickly.core.client.gpt.dto.GptModerationRequestDTO;
import ru.brickly.core.client.gpt.dto.GptModerationResponseDTO;
import ru.brickly.core.client.gpt.exception.GptClientException;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class GptModerationClient {

    private final GptModerationConfig config;
    private final WebClient gptWebClient;

    public String moderate(String text) {
        log.info("Calling GPT for moderation, text length: {}", text.length());

        try {
            // 1. Создаем запрос
            GptModerationRequestDTO request = new GptModerationRequestDTO(text);
            request.setModel(config.getModel());

            // 2. Вызываем GPT
            GptModerationResponseDTO response = gptWebClient.post()
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(GptModerationResponseDTO.class)
                    .timeout(Duration.ofMillis(config.getTimeout()))
                    .block(); // блокируем, потому что мы в фоновом воркере

            // 3. Извлекаем ответ
            String answer = response.getContent();

            if (answer == null || answer.isBlank()) {
                throw new GptClientException("Empty response from GPT");
            }

            log.info("GPT response: {}", answer);
            return answer;

        } catch (WebClientResponseException e) {
            log.error("GPT API error: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new GptClientException("GPT API error: " + e.getStatusCode(), e);

        } catch (Exception e) {
            log.error("Failed to call GPT", e);
            throw new GptClientException("Failed to call GPT");
        }
    }
}
