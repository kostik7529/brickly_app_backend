package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ElementDefaultDTO {
    private String id;
    private PartDefaultDTO part;
    private ColorShortDTO color;
    private Integer designId;
}
