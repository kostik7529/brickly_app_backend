package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.MeetingCreateRequest;
import ru.brickly.core.dto.MeetingDefaultDTO;
import ru.brickly.core.dto.MeetingUpdateDTO;

import java.util.List;

public interface MeetingService {
    List<MeetingDefaultDTO> getAllMeetings();

    MeetingDefaultDTO getMeetingById(Long id);

    Page<MeetingDefaultDTO> getAllMeetingsPaginated(Pageable pageable);

    MeetingDefaultDTO createMeeting(MeetingCreateRequest request);

    MeetingDefaultDTO updateMeeting(Long id, MeetingUpdateDTO dto);

    void deleteMeetingById(Long id);
}
