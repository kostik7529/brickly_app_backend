package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.TicketCreateDTO;
import ru.brickly.core.dto.TicketDefaultDTO;
import ru.brickly.core.dto.TicketUpdateDTO;
import ru.brickly.core.service.TicketService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/tickets")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketDefaultDTO> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping("/by_user_id/{userId}")
    public ResponseEntity<List<TicketDefaultDTO>> getAllTicketsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(ticketService.getAllTicketsByUserId(userId));
    }

    @GetMapping("/by_user_id/{userId}/paginated")
    public ResponseEntity<Page<TicketDefaultDTO>> getAllTicketsByUserIdPaginated(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ticketService.getAllTicketsByUserIdPaginated(userId, pageable));
    }

    @GetMapping("/by_meeting_id/{meetingId}")
    public ResponseEntity<List<TicketDefaultDTO>> getAllTicketsByMeetingId(@PathVariable Long meetingId) {
        return ResponseEntity.ok(ticketService.getAllTicketsByMeetingId(meetingId));
    }

    @GetMapping("/by_meeting_id/{meetingId}/paginated")
    public ResponseEntity<Page<TicketDefaultDTO>> getAllTicketsByMeetingIdPaginated(@PathVariable Long meetingId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ticketService.getAllTicketsByMeetingIdPaginated(meetingId, pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<TicketDefaultDTO> createTicket(@RequestBody TicketCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TicketDefaultDTO> updateTicket(@PathVariable Long id, @RequestBody TicketUpdateDTO dto) {
        return ResponseEntity.ok(ticketService.updateTicket(id, dto));
    }
}
