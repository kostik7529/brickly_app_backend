package ru.brickly.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FeedbackCreateDTO {
    @JsonProperty("target_id")
    private long targetId;

    @JsonProperty("author_id")
    private long authorId;

    private int rate;
    private String comment;
}
