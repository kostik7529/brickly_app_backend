package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventoryCreateDTO {
    private int id;
    private int version;
    private String ownerId;
}
