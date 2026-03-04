package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.support.AbstractExpressionPointcut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.PartCategoryCreateDTO;
import ru.brickly.core.dto.PartCategoryDefaultDTO;
import ru.brickly.core.dto.PartCategoryUpdateDTO;
import ru.brickly.core.service.PartCategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/parts/categories")
public class PartCategoryController {
    private final PartCategoryService partCategoryService;

    @GetMapping
    public ResponseEntity<List<PartCategoryDefaultDTO>> getAllPartCategories() {
        return ResponseEntity.ok(partCategoryService.getAllPartCategories());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<PartCategoryDefaultDTO>> getPartCategoriesPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(partCategoryService.getPartCategoriesPaginated(pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<PartCategoryDefaultDTO> createPartCategory(@RequestBody PartCategoryCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partCategoryService.createPartCategory(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PartCategoryDefaultDTO> updatePartCategory(@PathVariable int id, @RequestBody PartCategoryUpdateDTO dto) {
        return ResponseEntity.ok(partCategoryService.updatePartCategory(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePartCategoryById(@PathVariable int id) {
        partCategoryService.deletePartCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
