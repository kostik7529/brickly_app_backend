package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.TicketCreateDTO;
import ru.brickly.core.dto.TicketDefaultDTO;
import ru.brickly.core.entity.Ticket;

@UtilityClass
public class TicketMapper {
    public TicketDefaultDTO convertToDefaultDto(Ticket ticket) {
        TicketDefaultDTO ticketDefaultDTO = new TicketDefaultDTO();
        ticketDefaultDTO.setId(ticket.getId());
        ticketDefaultDTO.setUser(UserMapper.convertToShortDto(ticket.getUser()));
        ticketDefaultDTO.setMeeting(MeetingMapper.convertToShortDto(ticket.getMeeting()));
        ticketDefaultDTO.setPricePaid(ticket.getPricePaid());
        ticketDefaultDTO.setState(ticket.getState());
        return ticketDefaultDTO;
    }
}
