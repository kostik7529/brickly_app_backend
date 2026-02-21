package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.FeedbackDefaultDTO;

import java.util.List;

public interface FeedbackService {
    List<FeedbackDefaultDTO> getAllTargetFeedbacks(Long target_id);

    Page<FeedbackDefaultDTO> getAllTargetFeedbacksPaginated(Long target_id, Pageable pageable);
}
