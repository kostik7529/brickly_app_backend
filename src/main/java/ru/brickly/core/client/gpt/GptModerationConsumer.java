package ru.brickly.core.client.gpt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.brickly.core.entity.Feedback;
import ru.brickly.core.exception.FeedbackNotFoundException;
import ru.brickly.core.repository.FeedbackRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class GptModerationConsumer {
    private final FeedbackRepository feedbackRepository;
    private final GptModerationClient gptClient;  // пока нет, создадим дальше

    @RabbitListener(queues = "moderation.queue", concurrency = "1-2")
    public void processModeration(Long id) {
        log.info("Starting moderation for feedback: {}", id);

        // 1. Получаем отзыв из БД
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new FeedbackNotFoundException("Feedback with id " + id + " not found!"));
        if (feedback == null) {
            log.info("Terrible fuck!");
            return;
        }

        // 2. Зовем GPT
        String result = gptClient.moderate(feedback.getComment());  // синхронно, тут и надо

        // 3. Сохраняем результат
        feedback.setModeration(result);
        feedbackRepository.save(feedback);

        log.info("Moderation completed for feedback: {}, result: {}", id, result);
    }
}
