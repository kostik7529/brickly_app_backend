package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.InventoryPartCreateDTO;
import ru.brickly.core.dto.InventoryPartDefaultDTO;
import ru.brickly.core.dto.PartFromItemDTO;
import ru.brickly.core.service.InventoryPartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/inventories/parts")
public class InventoryPartController {
    private final InventoryPartService inventoryPartService;

    @GetMapping("/by_inventory_id/{id}")
    public ResponseEntity<Page<InventoryPartDefaultDTO>> getPartsByInventoryIdPaginated(@PathVariable int id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventoryPartService.getPartsByInventoryIdPaginated(id, pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<InventoryPartDefaultDTO> createInventoryPart(@RequestBody InventoryPartCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryPartService.createInventoryPart(dto));
    }

    @GetMapping("/from_set/{setId}")
    public ResponseEntity<Page<PartFromItemDTO>> getPartsBySetIdPaginated(@PathVariable String setId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "150") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventoryPartService.getPartsBySetIdPaginated(setId, pageable));
    }

    @GetMapping("/from_minifig/{minifigId}")
    public ResponseEntity<Page<PartFromItemDTO>> getPartsByMinifigIdPaginated(@PathVariable String minifigId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "150") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventoryPartService.getPartsByMinifigIdPaginated(minifigId, pageable));
    }
}
