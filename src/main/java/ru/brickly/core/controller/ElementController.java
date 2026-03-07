package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.ElementCreateDTO;
import ru.brickly.core.dto.ElementDefaultDTO;
import ru.brickly.core.dto.ElementUpdateDTO;
import ru.brickly.core.service.ElementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/elements")
public class ElementController {
    private final ElementService elementService;

    @GetMapping("/paginated")
    public ResponseEntity<Page<ElementDefaultDTO>> getElementsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(elementService.getElementsPaginated(pageable));
    }

    @GetMapping("/by_color_id/{colorId}")
    public ResponseEntity<Page<ElementDefaultDTO>> getElementsByColorIdPaginated(@PathVariable Integer colorId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(elementService.getElementsByColorIdPaginated(colorId, pageable));
    }

    @GetMapping("/by_part_id/{partId}")
    public ResponseEntity<Page<ElementDefaultDTO>> getElementsByPartIdPaginated(@PathVariable String partId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(elementService.getElementsByPartIdPaginated(partId, pageable));
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<ElementDefaultDTO> getElementById(@PathVariable String id) {
        return ResponseEntity.ok(elementService.getElementById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ElementDefaultDTO> createElement(@RequestBody ElementCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(elementService.createElement(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ElementDefaultDTO> updateElement(@PathVariable String id, @RequestBody ElementUpdateDTO dto) {
        return ResponseEntity.ok(elementService.updateElement(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteElementById(@PathVariable String id) {
        elementService.deleteElementById(id);
        return ResponseEntity.noContent().build();
    }
}
