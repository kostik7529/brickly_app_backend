package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class MeetingCreateDTO {
    private OffsetDateTime date;
    private String title;
    private OffsetDateTime announceDate;
    private Integer duration;
    private String address;
    private int typeId;
    private int ticketPrice;
    private String description;
    private Integer discountDuration;
    private Integer discountAmount;
    private Integer discountModifier;
}
