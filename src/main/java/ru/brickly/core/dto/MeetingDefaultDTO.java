package ru.brickly.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingDefaultDTO {
    private long id;
    private LocalDateTime date;
    private String address;
    private MeetingTypeDefaultDTO type;
    private int ticketPrice;
    private String description;
    private Integer discountDuration;
    private Integer discountAmount;
    private Integer discountModifier;
}
