package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "amount")
    private String amount;

    @Column(name = "status")
    private String status;

    @Column(name = "yookassa_payment_id")
    private String yookassaPaymentId;

    @Column(name = "credited")
    private boolean credited;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
