package ru.brickly.core.dto;

import lombok.Data;

@Data
public class PartAsChildRelationshipDTO {
    private long id;
    private String type;
    private String childPartId;
    private PartDefaultDTO parentPart;
}
