package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.InventoryMinifigDefaultDTO;
import ru.brickly.core.dto.MinifigContainingPartDTO;
import ru.brickly.core.dto.MinifigFromSetDTO;
import ru.brickly.core.service.InventoryMinifigService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("app/api/inventories/minifigs")
public class InventoryMinifigController {
    private final InventoryMinifigService inventoryMinifigService;

    @GetMapping("/by_inventory_id/{inventoryId}")
    public ResponseEntity<Page<InventoryMinifigDefaultDTO>> getInventoryMinifigsByInventoryIdPaginated(@PathVariable int inventoryId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventoryMinifigService.getInventoryMinifigsByInventoryIdPaginated(inventoryId, pageable));
    }

    @GetMapping("/containing_part/{partId}")
    public ResponseEntity<Page<MinifigContainingPartDTO>> getMinifigsContainingPart(@PathVariable String partId, @RequestParam(required = false) Integer colorId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventoryMinifigService.getMinifigsContainingPartPaginated(partId, colorId, pageable));
    }

    @GetMapping("/from_set/{setId}")
    public ResponseEntity<List<MinifigFromSetDTO>> getAllMinifigsFromSet(@PathVariable String setId) {
        return ResponseEntity.ok(inventoryMinifigService.getAllMinifigsFromSet(setId));
    }
}
