package ru.brickly.core.dto;

import lombok.Data;
import ru.brickly.core.entity.User;

@Data
public class CartItemDefaultDTO {
    private long id;
    private long userId;
    private String itemType;
    private int itemId;
    private int quantity;
}
