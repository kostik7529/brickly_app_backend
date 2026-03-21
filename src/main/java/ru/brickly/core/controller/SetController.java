package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.SetCreateDTO;
import ru.brickly.core.dto.SetDefaultDTO;
import ru.brickly.core.dto.SetUpdateDTO;
import ru.brickly.core.service.SetService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/sets")
public class SetController {
    private final SetService setService;

    @GetMapping("/paginated")
    public ResponseEntity<Page<SetDefaultDTO>> getSetsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(setService.getSetsPaginated(pageable));
    }

    @GetMapping("/by_theme_id/{themeId}")
    public ResponseEntity<Page<SetDefaultDTO>> getSetsByThemeIdPaginated(@PathVariable Integer themeId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(setService.getSetsByThemeIdPaginated(themeId, pageable));
    }

    @GetMapping("/by_year/{year}")
    public ResponseEntity<Page<SetDefaultDTO>> getSetsByYearPaginated(@PathVariable Integer year, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(setService.getSetsByYearPaginated(year, pageable));
    }

    @GetMapping("/by_name/{name}")
    public ResponseEntity<Page<SetDefaultDTO>> getSetsByNameContainingPaginated(@PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(setService.getSetsByNameContainingPaginated(name, pageable));
    }

    @GetMapping("/by_year_between/{startYear}/{endYear}")
    public ResponseEntity<Page<SetDefaultDTO>> getSetsByYearBetweenPaginated(@PathVariable Integer startYear, @PathVariable Integer endYear, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(setService.getSetsByYearBetweenPaginated(startYear, endYear, pageable));
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<SetDefaultDTO> getSetById(@PathVariable String id) {
        return ResponseEntity.ok(setService.getSetById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<SetDefaultDTO> createSet(@RequestBody SetCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(setService.createSet(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SetDefaultDTO> updateSet(@PathVariable String id, @RequestBody SetUpdateDTO dto) {
        return ResponseEntity.ok(setService.updateSet(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSetById(@PathVariable String id) {
        setService.deleteSetById(id);
        return ResponseEntity.noContent().build();
    }
}