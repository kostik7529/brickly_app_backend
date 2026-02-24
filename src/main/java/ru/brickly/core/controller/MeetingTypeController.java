package ru.brickly.core.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.brickly.core.dto.MeetingTypeDefaultDTO;
import ru.brickly.core.service.MeetingTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/meetings/types")
public class MeetingTypeController {
    private final MeetingTypeService meetingTypeService;

    @GetMapping
    public ResponseEntity<List<MeetingTypeDefaultDTO>> getAllMeetingTypes() {
        return ResponseEntity.ok(meetingTypeService.getAllMeetingTypes());
    }
}
