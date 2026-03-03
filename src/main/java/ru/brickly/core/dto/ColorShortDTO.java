package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ColorShortDTO {
    private int id;
    private String name;
    private String rgb;
    private boolean isTransparent;
}
