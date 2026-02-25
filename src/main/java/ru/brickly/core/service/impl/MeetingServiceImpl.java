package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.MeetingCreateDTO;
import ru.brickly.core.dto.MeetingDefaultDTO;
import ru.brickly.core.dto.MeetingUpdateDTO;
import ru.brickly.core.entity.Meeting;
import ru.brickly.core.entity.MeetingType;
import ru.brickly.core.exception.MeetingNotFoundException;
import ru.brickly.core.exception.MeetingTypeNotFoundException;
import ru.brickly.core.repository.MeetingRepository;
import ru.brickly.core.repository.MeetingTypeRepository;
import ru.brickly.core.service.MeetingService;
import ru.brickly.core.util.MeetingMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingTypeRepository meetingTypeRepository;

    @Override
    public List<MeetingDefaultDTO> getAllMeetings() {
        return meetingRepository.findAll().stream().map(MeetingMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public MeetingDefaultDTO getMeetingById(Long id) {
        return MeetingMapper.convertToDefaultDto(meetingRepository.findById(id).orElseThrow(() -> new MeetingNotFoundException("Meeting with id " + id + " not found!")));
    }

    @Override
    public Page<MeetingDefaultDTO> getAllMeetingsPaginated(Pageable pageable) {
        return meetingRepository.findAll(pageable).map(MeetingMapper::convertToDefaultDto);
    }

    @Override
    public MeetingDefaultDTO createMeeting(MeetingCreateDTO dto) {
        MeetingType meetingType = meetingTypeRepository.findById(dto.getTypeId()).orElseThrow(() -> new MeetingTypeNotFoundException("Meeting type with id " + dto.getTypeId() + " not found!"));
        Meeting meeting = new Meeting();
        meeting.setAddress(dto.getAddress());
        meeting.setDate(dto.getDate());
        meeting.setDescription(dto.getDescription());
        meeting.setType(meetingType);
        meeting.setDiscountAmount(dto.getDiscountAmount());
        meeting.setDiscountDuration(dto.getDiscountDuration());
        meeting.setDiscountModifier(dto.getDiscountModifier());
        meeting.setTicketPrice(dto.getTicketPrice());
        return MeetingMapper.convertToDefaultDto(meetingRepository.save(meeting));
    }

    @Override
    public MeetingDefaultDTO updateMeeting(Long id, MeetingUpdateDTO dto) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new MeetingNotFoundException("Meeting with id " + id + " not found!"));
        MeetingType meetingType = meetingTypeRepository.findById(dto.getTypeId()).orElseThrow(() -> new MeetingTypeNotFoundException("Meeting type with id " + dto.getTypeId() + " not found!"));
        meeting.setAddress(dto.getAddress());
        meeting.setDate(dto.getDate());
        meeting.setDescription(dto.getDescription());
        meeting.setType(meetingType);
        meeting.setDiscountAmount(dto.getDiscountAmount());
        meeting.setDiscountDuration(dto.getDiscountDuration());
        meeting.setDiscountModifier(dto.getDiscountModifier());
        meeting.setTicketPrice(dto.getTicketPrice());
        return MeetingMapper.convertToDefaultDto(meetingRepository.save(meeting));
    }

    @Override
    public void deleteMeetingById(Long id) {
        meetingRepository.findById(id).orElseThrow(() -> new MeetingNotFoundException("Meeting with id " + id + " not found!"));
        meetingRepository.deleteById(id);
    }
}
