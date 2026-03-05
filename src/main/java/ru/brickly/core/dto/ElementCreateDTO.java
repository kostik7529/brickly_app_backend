package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ElementCreateDTO {
    private String id;
    private String partId;
    private int colorId;
    private Integer designId;
}
