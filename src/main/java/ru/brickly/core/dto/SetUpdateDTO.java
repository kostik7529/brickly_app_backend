package ru.brickly.core.dto;

import lombok.Data;

@Data
public class SetUpdateDTO {
    private String name;
    private Integer year;
    private Integer themeId;
    private Integer numParts;
}