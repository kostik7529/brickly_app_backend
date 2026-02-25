package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.MeetingDiscountDefaultDTO;
import ru.brickly.core.entity.MeetingDiscount;

@UtilityClass
public class MeetingDiscountMapper {
    public MeetingDiscountDefaultDTO convertToDefaultDto(MeetingDiscount meetingDiscount) {
        MeetingDiscountDefaultDTO meetingDiscountDefaultDTO = new MeetingDiscountDefaultDTO();

        meetingDiscountDefaultDTO.setId(meetingDiscount.getId());
        meetingDiscountDefaultDTO.setDuration(meetingDiscount.getDuration());
        meetingDiscountDefaultDTO.setAmount(meetingDiscount.getAmount());
        meetingDiscountDefaultDTO.setModifier(meetingDiscount.getModifier());
        return meetingDiscountDefaultDTO;
    }
}
