package ru.brickly.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.PartRelationship;

@Repository
public interface PartRelationshipRepository extends JpaRepository<PartRelationship, Integer> {
}
