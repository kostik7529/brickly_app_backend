package ru.brickly.core.dto;

import lombok.Data;

@Data
public class PartRelationshipCreateDTO {
    private String type;
    private String childPartId;
    private String parentPartId;
}
