package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.MeetingCreateDTO;
import ru.brickly.core.dto.MeetingDefaultDTO;
import ru.brickly.core.dto.MeetingUpdateDTO;
import ru.brickly.core.service.MeetingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    @GetMapping
    public ResponseEntity<List<MeetingDefaultDTO>> getAllMeetings() {
        return ResponseEntity.ok(meetingService.getAllMeetings());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<MeetingDefaultDTO>> getALlMeetingsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(meetingService.getAllMeetingsPaginated(pageable));
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<MeetingDefaultDTO> getMeetingById(@PathVariable Long id) {
        return ResponseEntity.ok(meetingService.getMeetingById(id ));
    }

    @PostMapping("/create")
    public ResponseEntity<MeetingDefaultDTO> createMeeting(@RequestBody MeetingCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingService.createMeeting(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MeetingDefaultDTO> updateMeeting(@PathVariable Long id, @RequestBody MeetingUpdateDTO dto) {
        return ResponseEntity.ok(meetingService.updateMeeting(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMeetingById(@PathVariable Long id) {
        meetingService.deleteMeetingById(id);
        return ResponseEntity.noContent().build();
    }
}
