package ru.brickly.core.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.FeedbackDefaultDTO;
import ru.brickly.core.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("api/app/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/by_target/{target_id}")
    public ResponseEntity<List<FeedbackDefaultDTO>> getAllTargetFeedbacks(@PathVariable Long target_id) {
        return ResponseEntity.ok(feedbackService.getAllTargetFeedbacks(target_id));
    }

    @GetMapping("/by_target/{target_id}/paginated")
    public ResponseEntity<Page<FeedbackDefaultDTO>> getAllTargetFeedbacksPaginated(@PathVariable Long target_id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(feedbackService.getAllTargetFeedbacksPaginated(target_id, pageable));
    }
}
