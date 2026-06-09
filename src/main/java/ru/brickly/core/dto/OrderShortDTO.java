package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class OrderShortDTO {
    private long id;
    private String shippingMethod;
    private String shippingAddress;
    private OffsetDateTime createdAt;
    private UserShortDTO user;
}
