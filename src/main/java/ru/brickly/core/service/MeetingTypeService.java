package ru.brickly.core.service;

import ru.brickly.core.dto.MeetingTypeDefaultDTO;

import java.util.List;

public interface MeetingTypeService {
    List<MeetingTypeDefaultDTO> getAllMeetingTypes();
}
