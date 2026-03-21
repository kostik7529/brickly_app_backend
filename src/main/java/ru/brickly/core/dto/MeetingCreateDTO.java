package ru.brickly.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingCreateDTO {
    private LocalDateTime date;
    private LocalDateTime announceDate;
    private Integer duration;
    private String address;
    private int typeId;
    private int ticketPrice;
    private String description;
    private Integer discountDuration;
    private Integer discountAmount;
    private Integer discountModifier;
}
