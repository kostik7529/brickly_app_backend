package ru.brickly.core.dto;

import lombok.Data;

@Data
public class InventorySetDefaultDTO {
    private long id;
    private SetDefaultDTO set;
    private int quantity;
}
