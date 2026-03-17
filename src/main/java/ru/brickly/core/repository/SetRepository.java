package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Set;

@Repository
public interface SetRepository extends JpaRepository<Set, String> {

    @Override
    @EntityGraph(attributePaths = {"theme"})
    Page<Set> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"theme"})
    Page<Set> findByThemeId(Integer themeId, Pageable pageable);

    @EntityGraph(attributePaths = {"theme"})
    Page<Set> findByYear(Integer year, Pageable pageable);

    @EntityGraph(attributePaths = {"theme"})
    Page<Set> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @EntityGraph(attributePaths = {"theme"})
    Page<Set> findByYearBetween(Integer startYear, Integer endYear, Pageable pageable);
}