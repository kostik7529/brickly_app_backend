package ru.brickly.core.client.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YookassaCreatePaymentResponseDTO {
    private String id;
    private String status;
    private Boolean paid;
    private Amount amount;
    private Confirmation confirmation;
    @JsonProperty("cancellation_details")
    private CancellationDetails cancellationDetails;

    @Data
    public static class Amount {
        private String value;
        private String currency;
    }

    @Data
    public static class Confirmation {
        private String type;
        @JsonProperty("confirmation_url")
        private String confirmationUrl;
    }

    @Data
    public static class CancellationDetails {
        private String party;
        private String reason;
    }
}