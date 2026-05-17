package ru.brickly.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MinifigFromSetDTO {
    private String id;
    private String name;
    private Integer numParts;
    private String imageUrl;
    private Integer quantity;
}
