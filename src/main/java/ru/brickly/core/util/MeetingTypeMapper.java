package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.MeetingTypeDefaultDTO;
import ru.brickly.core.entity.MeetingType;

@UtilityClass
public class MeetingTypeMapper {
    public MeetingTypeDefaultDTO convertToDefaultDto(MeetingType meetingType) {
        MeetingTypeDefaultDTO meetingTypeDefaultDTO = new MeetingTypeDefaultDTO();
        meetingTypeDefaultDTO.setId(meetingType.getId());
        meetingTypeDefaultDTO.setDescription(meetingType.getDescription());
        return meetingTypeDefaultDTO;
    }
}
