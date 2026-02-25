package ru.brickly.core.dto;

import lombok.Data;

@Data
public class MeetingDiscountDefaultDTO {
    private long id;
    private int duration;
    private int amount;
    private int modifier;
}
