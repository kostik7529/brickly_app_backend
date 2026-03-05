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
import ru.brickly.core.exception.PartNotFoundException;
import ru.brickly.core.repository.PartCategoryRepository;
import ru.brickly.core.repository.PartRepository;
import ru.brickly.core.service.PartService;
import ru.brickly.core.util.PartMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final PartCategoryRepository partCategoryRepository;

    @Override
    public List<PartDefaultDTO> getAllParts() {
        return partRepository.findAll().stream().map(PartMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<PartDefaultDTO> getPartsPaginated(Pageable pageable) {
        return partRepository.findAll(pageable).map(PartMapper::convertToDefaultDto);
    }

    @Override
    public List<PartDefaultDTO> getAllPartsWithIdContaining(String idContaining) {
        return partRepository.findByIdContaining(idContaining).stream().map(PartMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<PartDefaultDTO> getPartsWithIdContainingPaginated(Pageable pageable, String idContaining) {
        return partRepository.findByIdContaining(idContaining, pageable).map(PartMapper::convertToDefaultDto);
    }

    @Override
    public List<PartDefaultDTO> getAllPartsWithNameContaining(String nameContaining) {
        return partRepository.findByNameContaining(nameContaining).stream().map(PartMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<PartDefaultDTO> getPartsWithNameContainingPaginated(Pageable pageable, String nameContaining) {
        return partRepository.findByNameContaining(nameContaining, pageable).map(PartMapper::convertToDefaultDto);
    }

    @Override
    public PartDefaultDTO createPart(PartCreateDTO dto) {
        PartCategory partCategory = partCategoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new PartCategoryNotFoundException("Part category with id " + dto.getCategoryId() + " not found!"));
        Part part = new Part();
        part.setId(dto.getId());
        part.setCategory(partCategory);
        part.setName(dto.getName());
        return PartMapper.convertToDefaultDto(partRepository.save(part));
    }

    @Override
    public PartDefaultDTO updatePart(String id, PartUpdateDTO dto) {
        Part part = partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part with id " + id + " not found!"));
        PartCategory partCategory = partCategoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new PartCategoryNotFoundException("Part category with id " + dto.getCategoryId() + " not found!"));
        part.setId(dto.getId());
        part.setCategory(partCategory);
        part.setName(dto.getName());
        return PartMapper.convertToDefaultDto(partRepository.save(part));
    }

    @Override
    public void deletePartById(String id) {
        partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part with id " + id + " not found!"));
        partRepository.deleteById(id);
    }
}
