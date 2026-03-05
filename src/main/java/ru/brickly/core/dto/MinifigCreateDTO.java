package ru.brickly.core.dto;

import lombok.Data;

@Data
public class MinifigCreateDTO {
    private String id;
    private String name;
    private int numParts;
    private String imageUrl;
}
