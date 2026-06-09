package ru.brickly.core.service;

import ru.brickly.core.dto.AuthorityDefaultDTO;

import java.util.List;

public interface AuthorityService {
    List<AuthorityDefaultDTO> getAllAuthorities();
}
