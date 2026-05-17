package ru.brickly.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetContainingMinifigDTO {
    private String id;
    private String name;
    private Integer year;
    private Integer numParts;
    private String imageUrl;
    private Integer themeId;
    private String themeName;
    private Integer minifigQuantity;
}
