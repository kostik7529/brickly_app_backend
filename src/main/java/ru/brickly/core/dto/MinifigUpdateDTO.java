package ru.brickly.core.dto;

import lombok.Data;

@Data
public class MinifigUpdateDTO {
    private String name;
    private int numParts;
    private String imageUrl;
}
