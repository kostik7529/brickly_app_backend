package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Minifig;

@Repository
public interface MinifigRepository extends JpaRepository<Minifig, String>, MinifigQueryRepository {
    @EntityGraph(attributePaths = {"blMinifig"})
    Page<Minifig> findByNameContaining(String nameContaining, Pageable pageable);
}
