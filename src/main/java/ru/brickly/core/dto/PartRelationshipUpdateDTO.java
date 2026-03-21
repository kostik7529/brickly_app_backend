package ru.brickly.core.dto;

import lombok.Data;

@Data
public class PartRelationshipUpdateDTO {
    private String type;
    private String childPartId;
    private String parentPartId;
}
