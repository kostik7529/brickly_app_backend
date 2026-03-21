package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ElementUpdateDTO {
    private String partId;
    private Integer colorId;
    private Integer designId;
}
