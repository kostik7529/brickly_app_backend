package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventoryMinifigFullDTO {
    private long id;
    private int inventoryId;
    private String minifigId;
    private int quantity;
}
