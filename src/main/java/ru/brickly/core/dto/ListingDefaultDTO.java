package ru.brickly.core.dto;

import lombok.Data;

@Data
public class ListingDefaultDTO {
    private long id;
    private String status;
    private String itemType;
    private String itemId;
    private int quantity;
    private String description;
    private String condition;
    private int conditionRate;
    private int price;
    private int viewsCount;
}
