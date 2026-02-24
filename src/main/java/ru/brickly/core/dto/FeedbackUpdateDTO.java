package ru.brickly.core.dto;

import lombok.Data;

@Data
public class FeedbackUpdateDTO {
    private int rate;
    private String comment;
}
