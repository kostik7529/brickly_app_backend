package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Meeting;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Override
    @EntityGraph(attributePaths = {"type", "creator"})
    List<Meeting> findAll();

    @Override
    @EntityGraph(attributePaths = {"type", "creator"})
    Optional<Meeting> findById(Long id);

    @EntityGraph(attributePaths = {"type", "creator"})
    Page<Meeting> findAllByCreator_Id(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"type", "creator"})
    List<Meeting> findAllByDateAfter(OffsetDateTime date);
}
