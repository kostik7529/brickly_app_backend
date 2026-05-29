package ru.brickly.core.dto;

import lombok.Data;

@Data
public class OrderItemCreateDTO {
    private String status;
    private int price;
    private int quantity;
    private long listingId;
}
