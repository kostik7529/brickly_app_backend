package ru.brickly.core.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.FeedbackCreateDTO;
import ru.brickly.core.dto.FeedbackDefaultDTO;
import ru.brickly.core.dto.FeedbackUpdateDTO;
import ru.brickly.core.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("api/app/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/by_target_id/{targetId}")
    public ResponseEntity<List<FeedbackDefaultDTO>> getAllTargetFeedbacks(@PathVariable Long targetId) {
        return ResponseEntity.ok(feedbackService.getAllTargetFeedbacks(targetId));
    }

    @GetMapping("/by_target_id/{target_id}/paginated")
    public ResponseEntity<Page<FeedbackDefaultDTO>> getAllTargetFeedbacksPaginated(@PathVariable Long targetId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(feedbackService.getAllTargetFeedbacksPaginated(targetId, pageable));
    }

    @GetMapping("/by_author_id/{authorId}")
    public ResponseEntity<List<FeedbackDefaultDTO>> getAllAuthorFeedbacks(@PathVariable Long authorId) {
        return ResponseEntity.ok(feedbackService.getAllAuthorFeedbacks(authorId));
    }

    @GetMapping("/by_author_id/{authorId}/paginated")
    public ResponseEntity<Page<FeedbackDefaultDTO>> getAllAuthorFeedbacksPaginated(@PathVariable Long authorId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(feedbackService.getAllAuthorFeedbacksPaginated(authorId, pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<FeedbackDefaultDTO> createFeedback(@RequestBody FeedbackCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.createFeedback(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FeedbackDefaultDTO> updateFeedback(@PathVariable Long id, @RequestBody FeedbackUpdateDTO dto) {
        return ResponseEntity.ok(feedbackService.updateFeedback(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFeedbackById(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
