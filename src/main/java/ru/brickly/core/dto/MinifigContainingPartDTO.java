package ru.brickly.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MinifigContainingPartDTO {
    private String id;
    private String name;
    private int numParts;
    private String imageUrl;
    private Integer partQuantity;

    private String blId;
    private String blName;
    private String blCategoryName;
    private String blImageUrl;
    private String blUrl;
}
