package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.brickly.core.dto.AuthorityDefaultDTO;
import ru.brickly.core.service.AuthorityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/authorities")
public class AuthorityController {
    private final AuthorityService authorityService;

    @GetMapping
    public ResponseEntity<List<AuthorityDefaultDTO>> getAllAuthorities() {
        return ResponseEntity.ok(authorityService.getAllAuthorities());
    }
}
