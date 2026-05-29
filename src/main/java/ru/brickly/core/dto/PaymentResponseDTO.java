package ru.brickly.core.dto;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private String paymentId;
    private String status;
    private String confirmationUrl;
    private String cancellationReason;
}
