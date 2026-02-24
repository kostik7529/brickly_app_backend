package ru.brickly.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brickly.core.entity.MeetingType;

public interface MeetingTypeRepository extends JpaRepository<MeetingType, Integer> {
}
