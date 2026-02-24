package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.brickly.core.entity.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @EntityGraph(attributePaths = {"target", "author"})
    List<Feedback> findByTargetId(Long target_id);

    @EntityGraph(attributePaths = {"target", "author"})
    Page<Feedback> findByTargetId(Long target_id, Pageable pageable);

    @EntityGraph(attributePaths = {"target", "author"})
    List<Feedback> findByAuthorId(Long target_id);

    @EntityGraph(attributePaths = {"target", "author"})
    Page<Feedback> findByAuthorId(Long target_id, Pageable pageable);
}
