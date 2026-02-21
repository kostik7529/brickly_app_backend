package ru.brickly.core.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.FeedbackDefaultDTO;
import ru.brickly.core.repository.FeedbackRepository;
import ru.brickly.core.service.FeedbackService;
import ru.brickly.core.util.FeedbackMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Override
    public List<FeedbackDefaultDTO> getAllTargetFeedbacks(Long target_id) {
        return feedbackRepository.findByTargetId(target_id).stream().map(FeedbackMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<FeedbackDefaultDTO> getAllTargetFeedbacksPaginated(Long target_id, Pageable pageable) {
        return feedbackRepository.findByTargetId(target_id, pageable).map(FeedbackMapper::convertToDefaultDto);
    }
}
