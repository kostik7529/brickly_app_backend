package ru.brickly.core.dto;

import lombok.Data;

@Data
public class TicketDefaultDTO {
    private long id;
    private UserShortDTO user;
    private MeetingShortDTO meeting;
    private int pricePaid;
    private int state;
}
