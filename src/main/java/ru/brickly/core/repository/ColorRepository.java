package ru.brickly.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
}
