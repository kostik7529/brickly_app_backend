package ru.brickly.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Meeting;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Override
    @EntityGraph(attributePaths = {"meeting_type"})
    List<Meeting> findAll();
}
