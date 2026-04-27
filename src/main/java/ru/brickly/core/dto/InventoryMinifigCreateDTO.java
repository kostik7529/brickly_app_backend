package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventoryMinifigCreateDTO {
    private int inventoryId;
    private String minifigId;
    private int quantity;
}
