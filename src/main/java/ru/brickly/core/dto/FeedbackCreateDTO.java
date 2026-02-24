package ru.brickly.core.dto;

import lombok.Data;

@Data
public class FeedbackCreateDTO {
    private long target_id;
    private UserShortDTO author;
    private int rate;
    private String comment;
}
