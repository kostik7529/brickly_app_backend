package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ColorDefaultDTO {
    private int id;
    private String name;
    private String rgb;
    private boolean isTransparent;
    private Integer numParts;
    private Integer numSets;
    private Integer yearSince;
    private Integer yearTill;
}
