package ru.brickly.core.dto;

import lombok.Data;

@Data
public class SetDefaultDTO {
    private String id;
    private String name;
    private Integer year;
    private ThemeShortDTO theme;
    private Integer numParts;
}