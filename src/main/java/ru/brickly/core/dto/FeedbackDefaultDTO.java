package ru.brickly.core.dto;

import lombok.Data;

@Data
public class FeedbackDefaultDTO {
    private long id;
    private long target_id;
    private UserShortDTO author;
    private int rate;
    private String comment;
    private String moderation;
}
