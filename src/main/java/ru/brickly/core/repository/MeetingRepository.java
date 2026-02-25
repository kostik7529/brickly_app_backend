package ru.brickly.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
