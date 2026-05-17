package ru.brickly.core.dto;

import lombok.Data;

@Data
public class PartCreateDTO {
    private String id;
    private String name;
    private int categoryId;
}
