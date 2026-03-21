package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.PartRelationship;

import java.util.Optional;

@Repository
public interface PartRelationshipRepository extends JpaRepository<PartRelationship, Long> {
    @Override
    @EntityGraph(attributePaths = {"childPart", "childPart.category", "parentPart", "parentPart.category"})
    Optional<PartRelationship> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"childPart", "childPart.category", "parentPart", "parentPart.category"})
    Page<PartRelationship> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"childPart", "childPart.category", "parentPart", "parentPart.category"})
    Page<PartRelationship> findByParentPart_Id(String parentPartId, Pageable pageable);

    @EntityGraph(attributePaths = {"childPart", "childPart.category", "parentPart", "parentPart.category"})
    Page<PartRelationship> findByChildPart_Id(String parentPartId, Pageable pageable);
}
