package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Part;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, String>, PartQueryRepository {
    @Override
    @EntityGraph(attributePaths = {"category"})
    List<Part> findAll();

    @Override
    @EntityGraph(attributePaths = {"category"})
    Page<Part> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"category"})
    List<Part> findByIdContainingIgnoreCase(String idLike);

    @EntityGraph(attributePaths = {"category"})
    Page<Part> findByIdContainingIgnoreCase(String idLike, Pageable pageable);

    @EntityGraph(attributePaths = {"category"})
    List<Part> findByNameContainingIgnoreCase(String nameLike);

    @EntityGraph(attributePaths = {"category"})
    Page<Part> findByNameContainingIgnoreCase(String nameLike, Pageable pageable);
}
