package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventorySetFullDTO {
    private long id;
    private int inventoryId;
    private String setId;
    private int quantity;
}
