package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ThemeCreateDTO {
    private Integer id;
    private String name;
    private Integer parentId;
}
