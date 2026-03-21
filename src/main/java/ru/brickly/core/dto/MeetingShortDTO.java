package ru.brickly.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingShortDTO {
    private long id;
    private LocalDateTime date;
    private Integer duration;
    private String address;
    private String description;
    private MeetingTypeDefaultDTO type;
}
