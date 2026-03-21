package ru.brickly.core.dto;

import lombok.Data;

@Data
public class PartAsParentRelationshipDTO {
    private long id;
    private String type;
    private String parentPartId;
    private PartDefaultDTO childPart;
}
