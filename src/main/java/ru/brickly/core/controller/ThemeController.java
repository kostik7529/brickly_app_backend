package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.ThemeCreateDTO;
import ru.brickly.core.dto.ThemeDefaultDTO;
import ru.brickly.core.dto.ThemeUpdateDTO;
import ru.brickly.core.service.ThemeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/themes")
public class ThemeController {
    private final ThemeService themeService;

    @GetMapping("/paginated")
    public ResponseEntity<Page<ThemeDefaultDTO>> getThemesPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(themeService.getThemesPaginated(pageable));
    }

    @GetMapping("/by_name_containing")
    public ResponseEntity<Page<ThemeDefaultDTO>> getThemesByNameContaining(@RequestParam String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(themeService.getThemesByNameContainingPaginated(pageable, name));
    }

    @GetMapping("/paginated/root")
    public ResponseEntity<Page<ThemeDefaultDTO>> getRootThemesPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(themeService.getRootThemesPaginated(pageable));
    }

    @GetMapping("/by_parent_id/{parentId")
    public ResponseEntity<Page<ThemeDefaultDTO>> getChildThemesByParentIdPaginated(@PathVariable Integer parentId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(themeService.getChildThemesByParentIdPaginated(parentId, pageable));
    }

    @GetMapping("/by_id/{id")
    public ResponseEntity<ThemeDefaultDTO> getThemeById(@PathVariable Integer id) {
        return ResponseEntity.ok(themeService.getThemeById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ThemeDefaultDTO> createTheme(@RequestBody ThemeCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(themeService.createTheme((dto)));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ThemeDefaultDTO> updateTheme(@PathVariable Integer id, @RequestBody ThemeUpdateDTO dto) {
        return ResponseEntity.ok(themeService.updateTheme(id, dto));
    }

    @DeleteMapping("/delete/{id")
    public ResponseEntity<Void> deleteThemeById(@PathVariable Integer id) {
        themeService.deleteThemeById(id);
        return ResponseEntity.noContent().build();
    }
}

