package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.AuthorityDefaultDTO;
import ru.brickly.core.repository.AuthorityRepository;
import ru.brickly.core.service.AuthorityService;
import ru.brickly.core.util.AuthorityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    @Override
    public List<AuthorityDefaultDTO> getAllAuthorities() {
        return authorityRepository.findAll().stream().map(AuthorityMapper::convertToDefaultDto).collect(Collectors.toList());
    }
}
