package ru.brickly.core.dto;

import lombok.Data;

@Data
public class CartItemCreateDTO {
    private long userId;
    private String itemType;
    private int itemId;
    private int quantity;
}
