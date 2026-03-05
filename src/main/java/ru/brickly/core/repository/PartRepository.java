package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Part;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, String> {
    @Override
    @EntityGraph(attributePaths = {"category"})
    List<Part> findAll();

    @Override
    @EntityGraph(attributePaths = {"category"})
    Page<Part> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"category"})
    List<Part> findByIdContaining(String idLike);

    @EntityGraph(attributePaths = {"category"})
    Page<Part> findByIdContaining(String idLike, Pageable pageable);

    @EntityGraph(attributePaths = {"category"})
    List<Part> findByNameContaining(String nameLike);

    @EntityGraph(attributePaths = {"category"})
    Page<Part> findByNameContaining(String nameLike, Pageable pageable);
}
