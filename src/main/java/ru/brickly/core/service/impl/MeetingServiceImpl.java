package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.MeetingCreateRequest;
import ru.brickly.core.dto.MeetingDefaultDTO;
import ru.brickly.core.dto.MeetingUpdateDTO;
import ru.brickly.core.entity.Meeting;
import ru.brickly.core.entity.MeetingType;
import ru.brickly.core.exception.MeetingNotFoundException;
import ru.brickly.core.exception.MeetingTypeNotFoundException;
import ru.brickly.core.repository.MeetingRepository;
import ru.brickly.core.repository.MeetingTypeRepository;
import ru.brickly.core.service.ImageStorageService;
import ru.brickly.core.service.MeetingService;
import ru.brickly.core.util.MeetingMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingTypeRepository meetingTypeRepository;
    private final ImageStorageService imageStorageService;

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
    public MeetingDefaultDTO createMeeting(MeetingCreateRequest request) {
        MeetingType meetingType = meetingTypeRepository.findById(request.getTypeId()).orElseThrow(() -> new MeetingTypeNotFoundException("Meeting type with id " + request.getTypeId() + " not found!"));
        Meeting meeting = new Meeting();
        meeting.setAddress(request.getAddress());
        meeting.setDate(request.getDate());
        meeting.setTitle(request.getTitle());
        meeting.setAnnounceDate(request.getAnnounceDate());
        meeting.setDuration(request.getDuration());
        meeting.setDescription(request.getDescription());
        meeting.setType(meetingType);
        meeting.setDiscountAmount(request.getDiscountAmount());
        meeting.setDiscountDuration(request.getDiscountDuration());
        meeting.setDiscountModifier(request.getDiscountModifier());
        meeting.setTicketPrice(request.getTicketPrice());

        String imagePath = null;
        if (request.getPreviewImage() != null && !request.getPreviewImage().isEmpty()) {
            imagePath = imageStorageService.saveMeetingImage(request.getPreviewImage());
        }
        meeting.setPreviewImagePath(imagePath);

        return MeetingMapper.convertToDefaultDto(meetingRepository.save(meeting));
    }

    @Override
    public MeetingDefaultDTO updateMeeting(Long id, MeetingUpdateDTO dto) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new MeetingNotFoundException("Meeting with id " + id + " not found!"));

        if (dto.getTypeId() != null) {
            MeetingType meetingType = meetingTypeRepository.findById(dto.getTypeId()).orElseThrow(() -> new MeetingTypeNotFoundException("Meeting type with id " + dto.getTypeId() + " not found!"));
            meeting.setType(meetingType);
        }

        if (dto.getAddress() != null) {
            meeting.setAddress(dto.getAddress());
        }

        if (dto.getTitle() != null) {
            meeting.setTitle(dto.getTitle());
        }

        if (dto.getDate() != null) {
            meeting.setDate(dto.getDate());
        }

        if (dto.getDescription() != null) {
            meeting.setDescription(dto.getDescription());
        }

        if (dto.getDiscountAmount() != null) {
            meeting.setDiscountAmount(dto.getDiscountAmount());
        }

        if (dto.getDuration() != null) {
            meeting.setDuration(dto.getDuration());
        }

        if (dto.getDiscountDuration() != null) {
            meeting.setDiscountDuration(dto.getDiscountDuration());
        }

        if (dto.getDiscountModifier() != null) {
            meeting.setDiscountModifier(dto.getDiscountModifier());
        }

        if (dto.getTicketPrice() != null) {
            meeting.setTicketPrice(dto.getTicketPrice());
        }

        return MeetingMapper.convertToDefaultDto(meetingRepository.save(meeting));
    }

    @Override
    public void deleteMeetingById(Long id) {
        meetingRepository.findById(id).orElseThrow(() -> new MeetingNotFoundException("Meeting with id " + id + " not found!"));
        meetingRepository.deleteById(id);
    }
}
