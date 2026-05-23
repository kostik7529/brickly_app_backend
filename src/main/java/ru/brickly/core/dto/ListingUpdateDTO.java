package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ListingUpdateDTO {
    private String status;
    private Integer quantity;
    private String description;
    private Integer price;
    private Integer viewsCount;
}
