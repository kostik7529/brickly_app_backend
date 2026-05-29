package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.PaymentResponseDTO;
import ru.brickly.core.dto.PaymentTopUserBalanceUpDTO;
import ru.brickly.core.service.PaymentService;

@RestController
@RequestMapping("/api/app/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/top_user_balance_up")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentTopUserBalanceUpDTO dto) {
        return ResponseEntity.ok(paymentService.topUserBalanceUp(dto));
    }

    @GetMapping("/by_yookassa_id/{yooId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByYookassaId(@PathVariable String yooId) {
        return ResponseEntity.ok(paymentService.getPaymentByYookassaId(yooId));
    }
}