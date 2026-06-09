package ru.brickly.core.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.FeedbackCreateDTO;
import ru.brickly.core.dto.FeedbackDefaultDTO;
import ru.brickly.core.dto.FeedbackUpdateDTO;
import ru.brickly.core.entity.Feedback;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.FeedbackNotFoundException;
import ru.brickly.core.exception.FeedbackCreatingException;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.FeedbackRepository;
import ru.brickly.core.repository.UserRepository;
import ru.brickly.core.service.FeedbackService;
import ru.brickly.core.util.FeedbackMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public List<FeedbackDefaultDTO> getAllTargetFeedbacks(Long targetId) {
        return feedbackRepository.findByTargetId(targetId).stream().map(FeedbackMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<FeedbackDefaultDTO> getAllTargetFeedbacksPaginated(Long targetId, Pageable pageable) {
        return feedbackRepository.findByTargetId(targetId, pageable).map(FeedbackMapper::convertToDefaultDto);
    }

    @Override
    public List<FeedbackDefaultDTO> getAllAuthorFeedbacks(Long authorId) {
        return feedbackRepository.findByAuthorId(authorId).stream().map(FeedbackMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<FeedbackDefaultDTO> getAllAuthorFeedbacksPaginated(Long authorId, Pageable pageable) {
        return feedbackRepository.findByAuthorId(authorId, pageable).map(FeedbackMapper::convertToDefaultDto);
    }

    @Override
    public List<FeedbackDefaultDTO> getFeedbacksByAuthorIdAndTargetId(Long authorId, Long targetId) {
        return feedbackRepository.findByAuthorIdAndTargetId(authorId, targetId).stream().map(FeedbackMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public FeedbackDefaultDTO createFeedback(FeedbackCreateDTO dto) {
        User target = userRepository.findById(dto.getTargetId()).orElseThrow(() -> new UserNotFoundException("Target with id " + dto.getTargetId() + " not found!"));
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow(() -> new UserNotFoundException("Author with id " + dto.getAuthorId() + " not found!"));
        List<Feedback> feedbacks = feedbackRepository.findByAuthorIdAndTargetId(dto.getAuthorId(), dto.getTargetId());

        if (author.getId() == target.getId()) {
            throw new FeedbackCreatingException("Self feedbacks are not allowed!");
        }
        if (dto.getRate() > 5 || dto.getRate() < 1) {
            throw new FeedbackCreatingException("Rate must be in range 1-5!");
        }
        if (feedbacks.size() > 1) {
            throw new FeedbackCreatingException("Only one feedback from user to user!");
        }

        Feedback feedback = new Feedback();
        feedback.setAuthor(author);
        feedback.setModeration("PROCESSING");
        feedback.setTarget(target);
        feedback.setComment(dto.getComment());
        feedback.setRate(dto.getRate());
        Feedback result = feedbackRepository.save(feedback);

        rabbitTemplate.convertAndSend("moderation.queue", result.getId());

        return FeedbackMapper.convertToDefaultDto(result);
    }

    @Override
    public FeedbackDefaultDTO updateFeedback(Long id, FeedbackUpdateDTO dto) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new FeedbackNotFoundException("Feedback with id " + id + " not found!"));

        if (dto.getRate() != null) {
            feedback.setRate(dto.getRate());
        }

        if (dto.getComment() != null) {
            feedback.setComment(dto.getComment());
            feedback.setModeration("PROCESSING");
            rabbitTemplate.convertAndSend("moderation.queue", feedback.getId());
        } else if (dto.getModeration() != null) {
            feedback.setModeration(dto.getModeration());
        }

        return FeedbackMapper.convertToDefaultDto(feedbackRepository.save(feedback));
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.findById(id).orElseThrow(() -> new FeedbackNotFoundException("Feedback with id " + id + " not found!"));
        feedbackRepository.deleteById(id);
    }
}
