package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.FeedbackDefaultDTO;
import ru.brickly.core.entity.Feedback;

@UtilityClass
public class FeedbackMapper {
    public FeedbackDefaultDTO convertToDefaultDto(Feedback feedback) {
        FeedbackDefaultDTO feedbackDTO = new FeedbackDefaultDTO();
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setRate(feedback.getRate());
        feedbackDTO.setAuthor(UserMapper.convertToShortDto(feedback.getAuthor()));
        feedbackDTO.setComment(feedback.getComment());
        feedbackDTO.setTarget_id(feedback.getTarget().getId());
        feedbackDTO.setModeration(feedback.getModeration());
        return feedbackDTO;
    }
}
