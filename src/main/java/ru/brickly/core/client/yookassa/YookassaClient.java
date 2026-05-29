package ru.brickly.core.client.yookassa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.brickly.core.client.yookassa.dto.YookassaCreatePaymentRequestDTO;
import ru.brickly.core.client.yookassa.dto.YookassaCreatePaymentResponseDTO;

import java.time.Duration;
import java.util.UUID;

@Component
public class YookassaClient {
    private final WebClient webClient;

    public YookassaClient(@Qualifier("yookassaWebClient") WebClient webClient) {
        this.webClient = webClient;
    }


    public YookassaCreatePaymentResponseDTO createPayment(YookassaCreatePaymentRequestDTO request) {
        String idempotenceKey = UUID.randomUUID().toString();

        return webClient.post()
                .uri("/payments")
                .header("Idempotence-Key", idempotenceKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(YookassaCreatePaymentResponseDTO.class)
                .timeout(Duration.ofSeconds(15))
                .block();
    }

    public YookassaCreatePaymentResponseDTO getPaymentById(String paymentId) {
        return webClient.get()
                .uri("/payments/{id}", paymentId)
                .retrieve()
                .bodyToMono(YookassaCreatePaymentResponseDTO.class)
                .block();
    }
}