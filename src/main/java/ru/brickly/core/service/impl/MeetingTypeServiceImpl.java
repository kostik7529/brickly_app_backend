package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.MeetingTypeDefaultDTO;
import ru.brickly.core.repository.MeetingTypeRepository;
import ru.brickly.core.service.MeetingTypeService;
import ru.brickly.core.util.MeetingTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingTypeServiceImpl implements MeetingTypeService {
    private final MeetingTypeRepository meetingTypeRepository;


    @Override
    public List<MeetingTypeDefaultDTO> getAllMeetingTypes() {
        return meetingTypeRepository.findAll().stream().map(MeetingTypeMapper::convertToDefaultDto).collect(Collectors.toList());
    }
}
