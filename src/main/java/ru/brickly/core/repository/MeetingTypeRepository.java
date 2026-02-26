package ru.brickly.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.MeetingType;

@Repository
public interface MeetingTypeRepository extends JpaRepository<MeetingType, Integer> {
}
