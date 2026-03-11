package ru.brickly.core.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.FeedbackCreateDTO;
import ru.brickly.core.dto.FeedbackDefaultDTO;
import ru.brickly.core.dto.FeedbackUpdateDTO;
import ru.brickly.core.entity.Feedback;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.FeedbackNotFoundException;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.FeedbackRepository;
import ru.brickly.core.repository.UserRepository;
import ru.brickly.core.service.FeedbackService;
import ru.brickly.core.util.FeedbackMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    @Override
    public List<FeedbackDefaultDTO> getAllTargetFeedbacks(Long target_id) {
        return feedbackRepository.findByTargetId(target_id).stream().map(FeedbackMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<FeedbackDefaultDTO> getAllTargetFeedbacksPaginated(Long target_id, Pageable pageable) {
        return feedbackRepository.findByTargetId(target_id, pageable).map(FeedbackMapper::convertToDefaultDto);
    }

    @Override
    public List<FeedbackDefaultDTO> getAllAuthorFeedbacks(Long author_id) {
        return feedbackRepository.findByAuthorId(author_id).stream().map(FeedbackMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<FeedbackDefaultDTO> getAllAuthorFeedbacksPaginated(Long author_id, Pageable pageable) {
        return feedbackRepository.findByAuthorId(author_id, pageable).map(FeedbackMapper::convertToDefaultDto);
    }

    @Override
    public FeedbackDefaultDTO createFeedback(FeedbackCreateDTO dto) {
        User target = userRepository.findById(dto.getTarget_id()).orElseThrow(() -> new UserNotFoundException("Target with id " + dto.getTarget_id() + " not found!"));
        User author = userRepository.findById(dto.getAuthor_id()).orElseThrow(() -> new UserNotFoundException("Author with id " + dto.getTarget_id() + " not found!"));

        Feedback feedback = new Feedback();
        feedback.setAuthor(author);
        feedback.setTarget(target);
        feedback.setComment(dto.getComment());
        feedback.setRate(dto.getRate());
        return FeedbackMapper.convertToDefaultDto(feedbackRepository.save(feedback));
    }

    @Override
    public FeedbackDefaultDTO updateFeedback(Long id, FeedbackUpdateDTO dto) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new FeedbackNotFoundException("Feedback with id " + id + " not found!"));

        feedback.setRate(dto.getRate());
        feedback.setComment(dto.getComment());
        feedback.setModeration(dto.getModeration());
        return FeedbackMapper.convertToDefaultDto(feedbackRepository.save(feedback));
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.findById(id).orElseThrow(() -> new FeedbackNotFoundException("Feedback with id " + id + " not found!"));
        feedbackRepository.deleteById(id);
    }
}
