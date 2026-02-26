package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.TicketCreateDTO;
import ru.brickly.core.dto.TicketDefaultDTO;
import ru.brickly.core.dto.TicketUpdateDTO;

import java.util.List;

public interface TicketService {
    List<TicketDefaultDTO> getAllTicketsByMeetingId(Long meetingId);

    Page<TicketDefaultDTO> getAllTicketsByMeetingIdPaginated(Long meetingId, Pageable pageable);

    List<TicketDefaultDTO> getAllTicketsByUserId(Long userId);

    Page<TicketDefaultDTO> getAllTicketsByUserIdPaginated(Long userId, Pageable pageable);

    TicketDefaultDTO createTicket(TicketCreateDTO dto);

    TicketDefaultDTO updateTicket(Long id, TicketUpdateDTO dto);
}
