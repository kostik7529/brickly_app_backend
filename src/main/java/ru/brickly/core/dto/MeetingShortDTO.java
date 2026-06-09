package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class MeetingShortDTO {
    private long id;
    private OffsetDateTime date;
    private String previewImagePath;
    private String title;
    private Integer duration;
    private String address;
    private String description;
    private int registeredCount;
    private MeetingTypeDefaultDTO type;
}
