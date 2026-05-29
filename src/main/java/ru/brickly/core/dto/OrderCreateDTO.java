package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderCreateDTO {
    private String shippingMethod;
    private String shippingAddress;
    private OffsetDateTime createdAt;
    private long userId;
    private List<OrderItemCreateDTO> orderItems;
}
