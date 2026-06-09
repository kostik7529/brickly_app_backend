package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class MeetingDefaultDTO {
    private long id;
    private OffsetDateTime date;
    private String title;
    private OffsetDateTime announceDate;
    private String previewImagePath;
    private Integer duration;
    private String address;
    private int registeredCount;
    private MeetingTypeDefaultDTO type;
    private int ticketPrice;
    private String description;
    private Integer discountDuration;
    private Integer discountAmount;
    private Integer discountModifier;
}
