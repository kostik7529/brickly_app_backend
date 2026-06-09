package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.FeedbackCreateDTO;
import ru.brickly.core.dto.FeedbackDefaultDTO;
import ru.brickly.core.dto.FeedbackUpdateDTO;

import java.util.List;

public interface FeedbackService {
    List<FeedbackDefaultDTO> getAllTargetFeedbacks(Long targetId);

    Page<FeedbackDefaultDTO> getAllTargetFeedbacksPaginated(Long targetId, Pageable pageable);

    List<FeedbackDefaultDTO> getAllAuthorFeedbacks(Long authorId);

    Page<FeedbackDefaultDTO> getAllAuthorFeedbacksPaginated(Long authorId, Pageable pageable);

    List<FeedbackDefaultDTO> getFeedbacksByAuthorIdAndTargetId(Long authorId, Long targetId);

    FeedbackDefaultDTO createFeedback(FeedbackCreateDTO dto);

    FeedbackDefaultDTO updateFeedback(Long id, FeedbackUpdateDTO dto);

    void deleteFeedback(Long id);
}
