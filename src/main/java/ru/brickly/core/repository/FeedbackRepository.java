package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.brickly.core.entity.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @EntityGraph(attributePaths = {"target", "author"})
    List<Feedback> findByTargetId(Long targetId);

    @EntityGraph(attributePaths = {"target", "author"})
    Page<Feedback> findByTargetId(Long targetId, Pageable pageable);

    @EntityGraph(attributePaths = {"target", "author"})
    List<Feedback> findByAuthorId(Long authorId);

    @EntityGraph(attributePaths = {"target", "author"})
    Page<Feedback> findByAuthorId(Long authorId, Pageable pageable);

    @EntityGraph(attributePaths = {"target", "author"})
    List<Feedback> findByAuthorIdAndTargetId(Long authorId, Long targetId);
}
