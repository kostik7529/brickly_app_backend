package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.PartCategoryCreateDTO;
import ru.brickly.core.dto.PartCategoryDefaultDTO;
import ru.brickly.core.dto.PartCategoryUpdateDTO;
import ru.brickly.core.entity.PartCategory;
import ru.brickly.core.exception.PartCategoryIdAlreadyExistsException;
import ru.brickly.core.exception.PartCategoryNotFoundException;
import ru.brickly.core.repository.PartCategoryRepository;
import ru.brickly.core.service.PartCategoryService;
import ru.brickly.core.util.PartCategoryMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartCategoryServiceImpl implements PartCategoryService {
    private final PartCategoryRepository partCategoryRepository;

    @Override
    public PartCategoryDefaultDTO getPartCategoryById(Integer id) {
        return PartCategoryMapper.convertToDefaultDto(partCategoryRepository.findById(id).orElseThrow(() -> new PartCategoryNotFoundException("Part category with id " + id + " not found!")));
    }

    @Override
    public List<PartCategoryDefaultDTO> getAllPartCategories() {
        return partCategoryRepository.findAll().stream().map(PartCategoryMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<PartCategoryDefaultDTO> getPartCategoriesPaginated(Pageable pageable) {
        return partCategoryRepository.findAll(pageable).map(PartCategoryMapper::convertToDefaultDto);
    }

    @Override
    public PartCategoryDefaultDTO createPartCategory(PartCategoryCreateDTO dto) {
        Optional<PartCategory> partCategoryExistenceCheck = partCategoryRepository.findById(dto.getId());
        if (partCategoryExistenceCheck.isPresent()) {
            throw new PartCategoryIdAlreadyExistsException("Part category with id " + dto.getId() + " already exists!");
        }
        PartCategory partCategory = new PartCategory();
        partCategory.setId(dto.getId());
        partCategory.setName(dto.getName());
        return PartCategoryMapper.convertToDefaultDto(partCategoryRepository.save(partCategory));
    }

    @Override
    public PartCategoryDefaultDTO updatePartCategory(Integer id, PartCategoryUpdateDTO dto) {
        PartCategory partCategory = partCategoryRepository.findById(id).orElseThrow(() -> new PartCategoryNotFoundException("Part category with id " + id + " not found!"));
        partCategory.setName(dto.getName());
        return PartCategoryMapper.convertToDefaultDto(partCategoryRepository.save(partCategory));
    }

    @Override
    public void deletePartCategoryById(Integer id) {
        partCategoryRepository.findById(id).orElseThrow(() -> new PartCategoryNotFoundException("Part category with id " + id + " not found!"));
        partCategoryRepository.deleteById(id);
    }
}
