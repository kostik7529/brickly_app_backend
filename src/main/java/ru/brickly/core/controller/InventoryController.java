package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.InventoryCreateDTO;
import ru.brickly.core.dto.InventoryDefaultDTO;
import ru.brickly.core.dto.InventoryUpdateDTO;
import ru.brickly.core.service.InventoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/by_id/{id}")
    public ResponseEntity<InventoryDefaultDTO> getInventoryById(@PathVariable int id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<InventoryDefaultDTO>> getInventoriesPaginated(@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventoryService.getInventoriesPaginated(pageable));
    }

    @GetMapping("/by_owner_id/{ownerId}")
    public ResponseEntity<List<InventoryDefaultDTO>> getAllInventoriesByOwnerId(@PathVariable String ownerId) {
        return ResponseEntity.ok(inventoryService.getAllInventoriesByOwnerId(ownerId));
    }

    @PostMapping("/create")
    public ResponseEntity<InventoryDefaultDTO> createInventory(@RequestBody InventoryCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createInventory(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InventoryDefaultDTO> updateInventory(@PathVariable int id, @RequestBody InventoryUpdateDTO dto) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInventoryById(@PathVariable int id) {
        inventoryService.deleteInventoryById(id);
        return ResponseEntity.noContent().build();
    }
}
