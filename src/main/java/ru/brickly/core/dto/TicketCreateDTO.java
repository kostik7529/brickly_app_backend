package ru.brickly.core.dto;

import lombok.Data;

@Data
public class TicketCreateDTO {
    private long userId;
    private long meetingId;
    private int pricePaid;
    private int state;
}
