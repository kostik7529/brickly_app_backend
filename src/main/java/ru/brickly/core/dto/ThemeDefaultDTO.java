package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ThemeDefaultDTO {
    private Integer id;
    private String name;
    private ThemeShortDTO parent;

}