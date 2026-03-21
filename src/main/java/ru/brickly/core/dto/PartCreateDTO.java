package ru.brickly.core.dto;

import lombok.Data;
import ru.brickly.core.entity.PartCategory;

@Data
public class PartCreateDTO {
    private String id;
    private String name;
    private int categoryId;
    private String imgUrl;
}
