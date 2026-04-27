package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventoryPartDefaultDTO {
    private long id;
    private PartDefaultDTO part;
    private ColorShortDTO color;
    private int quantity;
    private boolean isSpare;
    private String imageUrl;
}
