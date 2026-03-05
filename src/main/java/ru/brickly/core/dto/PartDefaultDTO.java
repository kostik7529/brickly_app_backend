package ru.brickly.core.dto;

import lombok.Data;
import ru.brickly.core.entity.PartCategory;

@Data
public class PartDefaultDTO {
    private String id;
    private String name;
    private PartCategoryShortDTO category;
}
