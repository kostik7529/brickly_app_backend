package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.MinifigCreateDTO;
import ru.brickly.core.dto.MinifigDefaultDTO;
import ru.brickly.core.dto.MinifigUpdateDTO;
import ru.brickly.core.service.MinifigService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/minifigs")
public class MinifigController {
    private final MinifigService minifigService;

    @GetMapping("/paginated")
    public ResponseEntity<Page<MinifigDefaultDTO>> getMinifigsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(minifigService.getMinifigsPaginated(pageable));
    }

    @GetMapping("/by_name_containing/{nameContaining}")
    public ResponseEntity<Page<MinifigDefaultDTO>> getMinifigsByNameContaining(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String nameContaining) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(minifigService.getMinifigsByNameContaining(nameContaining, pageable));
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<MinifigDefaultDTO> getMinifigById(@PathVariable String id) {
        return ResponseEntity.ok(minifigService.getMinifigById(id));
    }

    @GetMapping("by_bl_id/{blId}")
    public ResponseEntity<List<MinifigDefaultDTO>> getMinifigsByBlId(@PathVariable String blId) {
        return ResponseEntity.ok(minifigService.getAllMinifigsByBlId(blId));
    }

    @PostMapping("/create")
    public ResponseEntity<MinifigDefaultDTO> createMinifig(@RequestBody MinifigCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(minifigService.createMinifig(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MinifigDefaultDTO> updateMinifig(@PathVariable String id, @RequestBody MinifigUpdateDTO dto) {
        return ResponseEntity.ok(minifigService.updateMinifig(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMinifigById(@PathVariable String id) {
        minifigService.deleteMinifigById(id);
        return ResponseEntity.noContent().build();
    }
}
