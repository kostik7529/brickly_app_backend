package ru.brickly.core.dto;

import lombok.Data;

@Data
public class OrderItemDefaultDTO {
    private long id;
    private String status;
    private int price;
    private int quantity;
    private long listingId;
    private long orderId;
}
