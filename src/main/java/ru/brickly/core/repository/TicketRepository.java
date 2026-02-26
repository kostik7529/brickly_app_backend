package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @EntityGraph(attributePaths = {"user", "meeting", "meeting.type"})
    List<Ticket> findByMeetingId(Long meetingId);

    @EntityGraph(attributePaths = {"user", "meeting", "meeting.type"})
    List<Ticket> findByUserId(Long userId);

    @EntityGraph(attributePaths = {"user", "meeting", "meeting.type"})
    Page<Ticket> findByMeetingId(Long meetingId, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "meeting", "meeting.type"})
    Page<Ticket> findByUserId(Long userId, Pageable pageable);
}
