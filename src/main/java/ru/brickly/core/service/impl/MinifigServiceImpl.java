package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.MinifigCreateDTO;
import ru.brickly.core.dto.MinifigDefaultDTO;
import ru.brickly.core.dto.MinifigUpdateDTO;
import ru.brickly.core.entity.Minifig;
import ru.brickly.core.exception.MinifigIdAlreadyExistsException;
import ru.brickly.core.exception.MinifigNotFoundException;
import ru.brickly.core.repository.MinifigRepository;
import ru.brickly.core.service.MinifigService;
import ru.brickly.core.util.MinifigMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MinifigServiceImpl implements MinifigService {
    private final MinifigRepository minifigRepository;

    @Override
    public MinifigDefaultDTO getMinifigById(String id) {
        return MinifigMapper.convertToDefaultDTO(minifigRepository.findById(id).orElseThrow(() -> new MinifigNotFoundException("Minifig with id " + id + " not found!")));
    }

    @Override
    public List<MinifigDefaultDTO> getAllMinifigsByBlId(String blId) {
        return minifigRepository.findAllByBlMinifig_Id(blId).stream().map(MinifigMapper::convertToDefaultDTO).collect(Collectors.toList());
    }

    @Override
    public Page<MinifigDefaultDTO> getMinifigsPaginated(Pageable pageable) {
        return minifigRepository.findAll(pageable).map(MinifigMapper::convertToDefaultDTO);
    }

    @Override
    public Page<MinifigDefaultDTO> getMinifigsByNameContaining(String nameContaining, Pageable pageable) {
        return minifigRepository.findByNameContaining(nameContaining, pageable).map(MinifigMapper::convertToDefaultDTO);
    }

    @Override
    public MinifigDefaultDTO createMinifig(MinifigCreateDTO dto) {
        Optional<Minifig> minifigExistenceCheck = minifigRepository.findById(dto.getId());
        if (minifigExistenceCheck.isPresent()) {
            throw new MinifigIdAlreadyExistsException("Minifig with id " + dto.getId() + " already exists!");
        }
        Minifig minifig = new Minifig();
        minifig.setId(dto.getId());
        minifig.setName(dto.getName());
        minifig.setImageUrl(dto.getImageUrl());
        minifig.setNumParts(dto.getNumParts());
        return MinifigMapper.convertToDefaultDTO(minifigRepository.save(minifig));
    }

    @Override
    public MinifigDefaultDTO updateMinifig(String id, MinifigUpdateDTO dto) {
        Minifig minifig = minifigRepository.findById(id).orElseThrow(() -> new MinifigNotFoundException("Minifig with id " + id + " not found!"));

        if (dto.getName() != null) {
            minifig.setName(dto.getName());
        }

        if (dto.getImageUrl() != null) {
            minifig.setImageUrl(dto.getImageUrl());
        }

        if (dto.getNumParts() != null) {
            minifig.setNumParts(dto.getNumParts());
        }

        return MinifigMapper.convertToDefaultDTO(minifigRepository.save(minifig));
    }

    @Override
    public void deleteMinifigById(String id) {
        minifigRepository.findById(id).orElseThrow(() -> new MinifigNotFoundException("Minifig with id " + id + " not found!"));
        minifigRepository.deleteById(id);
    }
}
