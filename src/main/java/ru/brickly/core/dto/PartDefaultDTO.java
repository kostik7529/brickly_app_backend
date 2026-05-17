package ru.brickly.core.dto;

import lombok.Data;

@Data
public class PartDefaultDTO {
    private String id;
    private String name;
    private PartCategoryShortDTO category;
}
