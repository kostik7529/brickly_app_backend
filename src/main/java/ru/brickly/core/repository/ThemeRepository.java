package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Integer> {

    @Override
    @EntityGraph(attributePaths = {"parent"})
    Page<Theme> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"parent"})
    Page<Theme> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @EntityGraph(attributePaths = {"parent"})
    Page<Theme>findByParentIsNull(Pageable pageable); //todo пересмотреть

    @EntityGraph(attributePaths = {"parent"})
    Page<Theme>findByParentId(Integer parentId, Pageable pageable);

}