package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventoryMinifigDefaultDTO {
    private long id;
    private MinifigDefaultDTO minifig;
    private int quantity;
}
