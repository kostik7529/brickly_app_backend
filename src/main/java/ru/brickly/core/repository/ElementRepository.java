package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Element;

@Repository
public interface ElementRepository extends JpaRepository<Element, String> {
    @Override
    @EntityGraph(attributePaths = {"color", "part", "part.category"})
    Page<Element> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"color", "part", "part.category"})
    Page<Element> findByColorId(Integer colorId, Pageable pageable);

    @EntityGraph(attributePaths = {"color", "part", "part.category"})
    Page<Element> findByPartId(String partId, Pageable pageable);
}
