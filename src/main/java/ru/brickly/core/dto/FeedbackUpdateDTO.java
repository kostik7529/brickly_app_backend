package ru.brickly.core.dto;

import lombok.Data;

@Data
public class FeedbackUpdateDTO {
    private Integer rate;
    private String comment;
    private String moderation;
}
