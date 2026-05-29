package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Minifig;
import ru.brickly.core.entity.Order;

import java.util.List;

@Repository
public interface MinifigRepository extends JpaRepository<Minifig, String>, MinifigQueryRepository {
    @EntityGraph(attributePaths = {"blMinifig"})
    Page<Minifig> findByNameContaining(String nameContaining, Pageable pageable);

    @EntityGraph(attributePaths = {"blMinifig"})
    List<Minifig> findAllByBlMinifig_Id(String blId);
}
