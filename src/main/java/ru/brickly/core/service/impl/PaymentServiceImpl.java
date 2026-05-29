package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brickly.core.client.yookassa.YookassaClient;
import ru.brickly.core.client.yookassa.dto.YookassaCreatePaymentRequestDTO;
import ru.brickly.core.client.yookassa.dto.YookassaCreatePaymentResponseDTO;
import ru.brickly.core.dto.PaymentResponseDTO;
import ru.brickly.core.dto.PaymentTopUserBalanceUpDTO;
import ru.brickly.core.entity.Payment;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.PaymentNotFoundException;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.PaymentRepository;
import ru.brickly.core.repository.UserRepository;
import ru.brickly.core.service.PaymentService;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final YookassaClient yookassaClient;

    @Override
    public PaymentResponseDTO topUserBalanceUp(PaymentTopUserBalanceUpDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new UserNotFoundException("User with id " + dto.getUserId() + " not found!"));

        YookassaCreatePaymentRequestDTO request = buildRequest(dto);
        YookassaCreatePaymentResponseDTO response = yookassaClient.createPayment(request);

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setCredited(false);
        payment.setAmount(dto.getAmount());
        payment.setCreatedAt(OffsetDateTime.now());
        payment.setYookassaPaymentId(response.getId());
        payment.setStatus(response.getStatus());
        paymentRepository.save(payment);

        PaymentResponseDTO responseDTO = new PaymentResponseDTO();
        responseDTO.setPaymentId(response.getId());
        responseDTO.setStatus(response.getStatus());

        if ("pending".equals(response.getStatus()) && response.getConfirmation() != null) {
            System.out.println(response.toString());
            responseDTO.setConfirmationUrl(response.getConfirmation().getConfirmationUrl());
        }

        if ("succeeded".equals(response.getStatus())) {
            user.setBalance(user.getBalance() + (int) Double.parseDouble(dto.getAmount()));
            userRepository.save(user);
        }

        if ("canceled".equals(response.getStatus()) && response.getCancellationDetails() != null) {
            responseDTO.setCancellationReason(response.getCancellationDetails().getReason());
        }

        return responseDTO;
    }

    @Override
    public PaymentResponseDTO getPaymentByYookassaId(String yooId) {
        YookassaCreatePaymentResponseDTO response = yookassaClient.getPaymentById(yooId);

        PaymentResponseDTO responseDTO = new PaymentResponseDTO();
        responseDTO.setPaymentId(response.getId());
        responseDTO.setStatus(response.getStatus());

        if ("pending".equals(response.getStatus()) && response.getConfirmation() != null) {
            System.out.println(response.toString());
            responseDTO.setConfirmationUrl(response.getConfirmation().getConfirmationUrl());
        }

        if ("succeeded".equals(response.getStatus())) {
            Payment payment = paymentRepository.findByYookassaPaymentId(yooId).orElseThrow(() -> new PaymentNotFoundException("Payment with yookassa_id " + yooId + " not found!"));
            User user = payment.getUser();
            user.setBalance(user.getBalance() + (int) Double.parseDouble(payment.getAmount()));
            userRepository.save(user);
        }

        if ("canceled".equals(response.getStatus()) && response.getCancellationDetails() != null) {
            responseDTO.setCancellationReason(response.getCancellationDetails().getReason());
        }

        return responseDTO;
    }

    private YookassaCreatePaymentRequestDTO buildRequest(PaymentTopUserBalanceUpDTO dto) {
        YookassaCreatePaymentRequestDTO request = new YookassaCreatePaymentRequestDTO();

        YookassaCreatePaymentRequestDTO.Amount amount = new YookassaCreatePaymentRequestDTO.Amount();
        amount.setValue(dto.getAmount());
        amount.setCurrency("RUB");

        request.setAmount(amount);
        request.setPaymentToken(dto.getPaymentToken());
        request.setCapture(true);
        request.setDescription("Пополнение баланса пользователя в приложении Brickly App");
        return request;
    }
}
