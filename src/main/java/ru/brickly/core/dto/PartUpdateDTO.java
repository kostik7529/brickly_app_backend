package ru.brickly.core.dto;

import lombok.Data;

@Data
public class PartUpdateDTO {
    private String id;
    private String name;
    private Integer categoryId;
}
