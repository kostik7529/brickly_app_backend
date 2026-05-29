package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderDefaultDTO {
    private long id;
    private String shippingMethod;
    private String shippingAddress;
    private OffsetDateTime createdAt;
    private UserShortDTO user;
    private List<OrderItemDefaultDTO> orderItems;
}
