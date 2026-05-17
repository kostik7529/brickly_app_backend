package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class MeetingUpdateDTO {
    private OffsetDateTime date;
    private String address;
    private String title;
    private Integer duration;
    private Integer typeId;
    private Integer ticketPrice;
    private String description;
    private Integer discountDuration;
    private Integer discountAmount;
    private Integer discountModifier;
}
