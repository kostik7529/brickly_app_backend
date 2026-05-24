package ru.brickly.core.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ListingCreateRequest {
    private String status;
    private long sellerId;
    private String itemType;
    private String itemId;
    private int quantity;
    private String description;
    private String condition;
    private int conditionRate;
    private int price;
    private List<MultipartFile> images;
}
