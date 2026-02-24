package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.FeedbackCreateDTO;
import ru.brickly.core.dto.FeedbackDefaultDTO;
import ru.brickly.core.dto.FeedbackUpdateDTO;

import java.util.List;

public interface FeedbackService {
    List<FeedbackDefaultDTO> getAllTargetFeedbacks(Long target_id);

    Page<FeedbackDefaultDTO> getAllTargetFeedbacksPaginated(Long target_id, Pageable pageable);

    List<FeedbackDefaultDTO> getAllAuthorFeedbacks(Long author_id);

    Page<FeedbackDefaultDTO> getAllAuthorFeedbacksPaginated(Long author_id, Pageable pageable);

    FeedbackDefaultDTO createFeedback(FeedbackCreateDTO dto);

    FeedbackDefaultDTO updateFeedback(Long id, FeedbackUpdateDTO dto);

    void deleteFeedback(Long id);
}
