package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventoryPartCreateDTO {
    private int inventoryId;
    private String partId;
    private int colorId;
    private int quantity;
    private boolean isSpare;
    private String imageUrl;
}
