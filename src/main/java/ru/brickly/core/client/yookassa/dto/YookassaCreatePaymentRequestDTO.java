package ru.brickly.core.client.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YookassaCreatePaymentRequestDTO {

    private Amount amount;

    @JsonProperty("payment_token")
    private String paymentToken;
    private Boolean capture;
    private String description;

    @Data
    public static class Amount {
        private String value;
        private String currency;
    }
}
