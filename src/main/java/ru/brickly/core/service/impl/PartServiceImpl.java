package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.PartCreateDTO;
import ru.brickly.core.dto.PartDefaultDTO;
import ru.brickly.core.dto.PartUpdateDTO;
import ru.brickly.core.entity.Part;
import ru.brickly.core.entity.PartCategory;
import ru.brickly.core.exception.PartCategoryNotFoundException;
import ru.brickly.core.exception.PartIdAlreadyExistsException;
import ru.brickly.core.exception.PartNotFoundException;
import ru.brickly.core.repository.PartCategoryRepository;
import ru.brickly.core.repository.PartRepository;
import ru.brickly.core.service.PartService;
import ru.brickly.core.util.PartMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final PartCategoryRepository partCategoryRepository;

    @Override
    public PartDefaultDTO getPartById(String id) {
        return PartMapper.convertToDefaultDto(partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part with id " + id + " not found!")));
    }

    @Override
    public List<PartDefaultDTO> getAllParts() {
        return partRepository.findAll().stream().map(PartMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<PartDefaultDTO> getPartsPaginated(Pageable pageable) {
        return partRepository.findAll(pageable).map(PartMapper::convertToDefaultDto);
    }

    @Override
    public List<PartDefaultDTO> getAllPartsByIdContaining(String idContaining) {
        return partRepository.findByIdContaining(idContaining).stream().map(PartMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<PartDefaultDTO> getPartsByIdContainingPaginated(Pageable pageable, String idContaining) {
        return partRepository.findByIdContaining(idContaining, pageable).map(PartMapper::convertToDefaultDto);
    }

    @Override
    public List<PartDefaultDTO> getAllPartsByNameContaining(String nameContaining) {
        return partRepository.findByNameContaining(nameContaining).stream().map(PartMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<PartDefaultDTO> getPartsByNameContainingPaginated(Pageable pageable, String nameContaining) {
        return partRepository.findByNameContaining(nameContaining, pageable).map(PartMapper::convertToDefaultDto);
    }

    @Override
    public PartDefaultDTO createPart(PartCreateDTO dto) {
        Optional<Part> partExistenceCheck = partRepository.findById(dto.getId());
        if (partExistenceCheck.isPresent()) {
            throw new PartIdAlreadyExistsException("Part with id " + dto.getId() + " already exists!");
        }
        PartCategory partCategory = partCategoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new PartCategoryNotFoundException("Part category with id " + dto.getCategoryId() + " not found!"));
        Part part = new Part();
        part.setId(dto.getId());
        part.setCategory(partCategory);
        part.setName(dto.getName());
        part.setImageUrl(dto.getImgUrl());
        return PartMapper.convertToDefaultDto(partRepository.save(part));
    }

    @Override
    public PartDefaultDTO updatePart(String id, PartUpdateDTO dto) {
        Part part = partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part with id " + id + " not found!"));

        if (dto.getCategoryId() != null) {
            PartCategory partCategory = partCategoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new PartCategoryNotFoundException("Part category with id " + dto.getCategoryId() + " not found!"));
            part.setCategory(partCategory);
        }

        if (dto.getName() != null) {
            part.setName(dto.getName());
        }

        return PartMapper.convertToDefaultDto(partRepository.save(part));
    }

    @Override
    public void deletePartById(String id) {
        partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part with id " + id + " not found!"));
        partRepository.deleteById(id);
    }
}
