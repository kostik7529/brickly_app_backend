package ru.brickly.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartFromItemDTO {
    private String id;
    private String name;
    private Integer categoryId;
    private String categoryName;
    private Integer colorId;
    private String colorName;
    private String colorRgb;
    private String imageUrl;
    private Integer quantity;
}
