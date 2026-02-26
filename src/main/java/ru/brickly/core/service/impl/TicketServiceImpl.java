package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.TicketCreateDTO;
import ru.brickly.core.dto.TicketDefaultDTO;
import ru.brickly.core.dto.TicketUpdateDTO;
import ru.brickly.core.entity.Meeting;
import ru.brickly.core.entity.Ticket;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.MeetingNotFoundException;
import ru.brickly.core.exception.MeetingTypeNotFoundException;
import ru.brickly.core.exception.TicketNotFoundException;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.MeetingRepository;
import ru.brickly.core.repository.TicketRepository;
import ru.brickly.core.repository.UserRepository;
import ru.brickly.core.service.TicketService;
import ru.brickly.core.util.TicketMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;

    @Override
    public List<TicketDefaultDTO> getAllTicketsByMeetingId(Long meetingId) {
        return ticketRepository.findByMeetingId(meetingId).stream().map(TicketMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<TicketDefaultDTO> getAllTicketsByMeetingIdPaginated(Long meetingId, Pageable pageable) {
        return ticketRepository.findByMeetingId(meetingId, pageable).map(TicketMapper::convertToDefaultDto);
    }

    @Override
    public List<TicketDefaultDTO> getAllTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId).stream().map(TicketMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<TicketDefaultDTO> getAllTicketsByUserIdPaginated(Long userId, Pageable pageable) {
        return ticketRepository.findByUserId(userId, pageable).map(TicketMapper::convertToDefaultDto);
    }

    @Override
    public TicketDefaultDTO createTicket(TicketCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new UserNotFoundException("User with id " + dto.getUserId() + " not found!"));
        Meeting meeting = meetingRepository.findById(dto.getMeetingId()).orElseThrow(() -> new MeetingNotFoundException("Meeting with id " + dto.getMeetingId() + " not found!"));
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMeeting(meeting);
        ticket.setPricePaid(dto.getPricePaid());
        ticket.setState(dto.getState());
        return TicketMapper.convertToDefaultDto(ticketRepository.save(ticket));
    }

    @Override
    public TicketDefaultDTO updateTicket(Long id, TicketUpdateDTO dto) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket with id " + id + " not found!"));
        ticket.setState(dto.getState());
        return TicketMapper.convertToDefaultDto(ticketRepository.save(ticket));
    }
}
