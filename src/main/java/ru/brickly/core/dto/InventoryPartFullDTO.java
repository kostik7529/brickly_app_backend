package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventoryPartFullDTO {
    private long id;
    private int inventoryId;
    private String partId;
    private int colorId;
    private int quantity;
    private boolean isSpare;
}
