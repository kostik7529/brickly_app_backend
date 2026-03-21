package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.*;
import ru.brickly.core.service.PartRelationshipService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/parts/relationships")
public class PartRelationshipController {
    private final PartRelationshipService partRelationShipService;

    @GetMapping("/paginated")
    public ResponseEntity<Page<PartRelationshipDefaultDTO>> getAllPartRelationshipsPaginated(@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(partRelationShipService.getAllPartRelationshipsPaginated(pageable));
    }

    @GetMapping("/by_child_part_id/{childPartId}")
    public ResponseEntity<Page<PartAsChildRelationshipDTO>> getAllRelationshipsByChildIdPaginated(@PathVariable String childPartId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(partRelationShipService.getAllRelationshipsByChildIdPaginated(childPartId, pageable));
    }

    @GetMapping("/by_parent_part_id/{parentPartId}")
    public ResponseEntity<Page<PartAsParentRelationshipDTO>> getAllRelationshipsByParentIdPaginated(@PathVariable String parentPartId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(partRelationShipService.getAllRelationshipsByParentIdPaginated(parentPartId, pageable));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PartRelationshipDefaultDTO> updatePartRelationship(@PathVariable long id, @RequestBody PartRelationshipUpdateDTO dto) {
        return ResponseEntity.ok(partRelationShipService.updatePartRelationship(id, dto));
    }

    @PostMapping("create")
    public ResponseEntity<PartRelationshipDefaultDTO> createPartRelationship(@RequestBody PartRelationshipCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partRelationShipService.createPartRelationship(dto));
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<Void> deletePartRelationshipById(@PathVariable long id) {
        partRelationShipService.deletePartRelationshipById(id);
        return ResponseEntity.noContent().build();
    }
}
