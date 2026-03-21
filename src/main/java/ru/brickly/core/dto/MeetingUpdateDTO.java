package ru.brickly.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingUpdateDTO {
    private LocalDateTime date;
    private String address;
    private Integer duration;
    private Integer typeId;
    private Integer ticketPrice;
    private String description;
    private Integer discountDuration;
    private Integer discountAmount;
    private Integer discountModifier;
}
