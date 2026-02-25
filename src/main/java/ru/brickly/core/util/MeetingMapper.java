package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.MeetingDefaultDTO;
import ru.brickly.core.entity.Meeting;

@UtilityClass
public class MeetingMapper {
    public MeetingDefaultDTO convertToDefaultDto(Meeting meeting) {
        MeetingDefaultDTO meetingDefaultDTO = new MeetingDefaultDTO();
        meetingDefaultDTO.setId(meeting.getId());
        meetingDefaultDTO.setDate(meeting.getDate());
        meetingDefaultDTO.setType(MeetingTypeMapper.convertToDefaultDto(meeting.getType()));
        meetingDefaultDTO.setAddress(meeting.getAddress());
        meetingDefaultDTO.setDescription(meeting.getDescription());
        meetingDefaultDTO.setTicketPrice(meeting.getTicketPrice());
        meetingDefaultDTO.setDiscountAmount(meeting.getDiscountAmount());
        meetingDefaultDTO.setDiscountDuration(meeting.getDiscountDuration());
        meetingDefaultDTO.setDiscountModifier(meeting.getDiscountModifier());
        return meetingDefaultDTO;
    }
}
