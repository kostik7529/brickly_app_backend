package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ThemeUpdateDTO {
    private String name;
    private Integer parentId;
}