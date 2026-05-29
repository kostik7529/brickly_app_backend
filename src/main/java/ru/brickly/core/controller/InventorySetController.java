package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.InventorySetDefaultDTO;
import ru.brickly.core.dto.SetContainingBLMinifigDTO;
import ru.brickly.core.dto.SetContainingBlPartDTO;
import ru.brickly.core.service.InventorySetService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/inventories/sets")
public class InventorySetController {
    private final InventorySetService inventorySetService;

    @GetMapping("/by_inventory_id/{inventoryId}")
    public ResponseEntity<Page<InventorySetDefaultDTO>> getInventorySetsByInventoryIdPaginated(@PathVariable int inventoryId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventorySetService.getInventorySetsByInventoryIdPaginated(inventoryId, pageable));
    }

    @GetMapping("/containing_part/{partId}")
    public ResponseEntity<Page<SetContainingBlPartDTO>> getSetsContainingPart(@PathVariable String partId, @RequestParam(required = false) Integer colorId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventorySetService.getSetsContainingPartPaginated(partId, colorId, pageable));
    }

    @GetMapping("/containing_minifig/{minifigId}")
    public ResponseEntity<Page<SetContainingBLMinifigDTO>> getSetsContainingMinifig(@PathVariable String minifigId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventorySetService.getSetsContainingMinifigPaginated(minifigId, pageable));
    }
}