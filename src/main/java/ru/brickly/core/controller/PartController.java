package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.PartCreateDTO;
import ru.brickly.core.dto.PartDefaultDTO;
import ru.brickly.core.dto.PartUpdateDTO;
import ru.brickly.core.service.PartService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/parts")
public class PartController {
    private final PartService partService;

    @GetMapping
    public ResponseEntity<List<PartDefaultDTO>> getAllParts() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<PartDefaultDTO>> getPartsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(partService.getPartsPaginated(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartDefaultDTO> getPartById(@PathVariable String id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }

    @GetMapping("/with_id_containing/{idContaining}")
    public ResponseEntity<List<PartDefaultDTO>> getAllPartsWithIdContaining(@PathVariable String idContaining) {
        return ResponseEntity.ok(partService.getAllPartsWithIdContaining(idContaining));
    }

    @GetMapping("/with_id_containing/{idContaining}/paginated")
    public ResponseEntity<Page<PartDefaultDTO>> getPartsWithIdContainingPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String idContaining) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(partService.getPartsWithIdContainingPaginated(pageable, idContaining));
    }

    @GetMapping("/with_name_containing/{nameContaining}")
    public ResponseEntity<List<PartDefaultDTO>> getAllPartsWithNameContaining(@PathVariable String nameContaining) {
        return ResponseEntity.ok(partService.getAllPartsWithNameContaining(nameContaining));
    }

    @GetMapping("/with_name_containing/{nameContaining}/paginated")
    public ResponseEntity<Page<PartDefaultDTO>> getPartsWithNameContainingPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String nameContaining) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(partService.getPartsWithNameContainingPaginated(pageable, nameContaining));
    }

    @PostMapping("/create")
    public ResponseEntity<PartDefaultDTO> createPart(@RequestBody PartCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.createPart(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PartDefaultDTO> updatePart(@PathVariable String id, @RequestBody PartUpdateDTO dto) {
        return ResponseEntity.ok(partService.updatePart(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePartById(@PathVariable String id) {
        partService.deletePartById(id);
        return ResponseEntity.noContent().build();
    }
}
