package ru.brickly.core.service;

import ru.brickly.core.dto.PaymentResponseDTO;
import ru.brickly.core.dto.PaymentTopUserBalanceUpDTO;

public interface PaymentService {
    PaymentResponseDTO topUserBalanceUp(PaymentTopUserBalanceUpDTO dto);

    PaymentResponseDTO getPaymentByYookassaId(String yooId);
}
