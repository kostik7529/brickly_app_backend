package ru.brickly.core.dto;

import lombok.Data;

@Data
public class PaymentTopUserBalanceUpDTO {
    private long userId;
    private String amount;
    private String paymentToken;
}
