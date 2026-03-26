package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventoryUpdateDTO {
    private Integer version;
    private String ownerId;
}
