package ru.brickly.core.dto;

import lombok.Data;

@Data
public class FeedbackCreateDTO {
    private long target_id;
    private long author_id;
    private int rate;
    private String comment;
}
