package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.ColorCreateDTO;
import ru.brickly.core.dto.ColorDefaultDTO;
import ru.brickly.core.dto.ColorShortDTO;
import ru.brickly.core.dto.ColorUpdateDTO;
import ru.brickly.core.service.ColorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/colors")
public class ColorController {
    private final ColorService colorService;

    @GetMapping("/default")
    public ResponseEntity<List<ColorDefaultDTO>> getAllColors() {
        return ResponseEntity.ok(colorService.getAllColors());
    }

    @GetMapping("/short")
    public ResponseEntity<List<ColorShortDTO>> getAllShortColors() {
        return ResponseEntity.ok(colorService.getAllShortColors());
    }

    @GetMapping("/default/paginated")
    public ResponseEntity<Page<ColorDefaultDTO>> getColorsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(colorService.getColorsPaginated(pageable));
    }

    @GetMapping("/short/paginated")
    public ResponseEntity<Page<ColorShortDTO>> getShortColorsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(colorService.getShortColorsPaginated(pageable));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ColorDefaultDTO> updateColor(@PathVariable Integer id, @RequestBody ColorUpdateDTO dto) {
        return ResponseEntity.ok(colorService.updateColor(id, dto));
    }

    @PostMapping("/create")
    public ResponseEntity<ColorDefaultDTO> createColor(@RequestBody ColorCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(colorService.createColor(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteColorById(@PathVariable Integer id) {
        colorService.deleteColorById(id);
        return ResponseEntity.noContent().build();
    }
}
