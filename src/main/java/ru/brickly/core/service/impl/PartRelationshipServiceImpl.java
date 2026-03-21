package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.*;
import ru.brickly.core.entity.Part;
import ru.brickly.core.entity.PartRelationship;
import ru.brickly.core.exception.PartNotFoundException;
import ru.brickly.core.exception.PartRelationshipNotFoundException;
import ru.brickly.core.repository.PartRelationshipRepository;
import ru.brickly.core.repository.PartRepository;
import ru.brickly.core.service.PartRelationshipService;
import ru.brickly.core.util.PartRelationshipMapper;

@Service
@RequiredArgsConstructor
public class PartRelationshipServiceImpl implements PartRelationshipService {
    private final PartRelationshipRepository partRelationshipRepository;
    private final PartRepository partRepository;

    @Override
    public Page<PartRelationshipDefaultDTO> getAllPartRelationshipsPaginated(Pageable pageable) {
        return partRelationshipRepository.findAll(pageable).map(PartRelationshipMapper::convertToDefaultDto);
    }

    @Override
    public Page<PartAsParentRelationshipDTO> getAllRelationshipsByParentIdPaginated(String parentPartId, Pageable pageable) {
        return partRelationshipRepository.findByParentPart_Id(parentPartId, pageable).map(PartRelationshipMapper::convertToAsParentDto);
    }

    @Override
    public Page<PartAsChildRelationshipDTO> getAllRelationshipsByChildIdPaginated(String childPartId, Pageable pageable) {
        return partRelationshipRepository.findByChildPart_Id(childPartId, pageable).map(PartRelationshipMapper::convertAsChildDto);
    }

    @Override
    public PartRelationshipDefaultDTO updatePartRelationship(Long id, PartRelationshipUpdateDTO dto) {
        PartRelationship partRelationship = partRelationshipRepository.findById(id).orElseThrow(() -> new PartRelationshipNotFoundException("Part relationship with id " + id + " not found!"));

        if (dto.getChildPartId() != null) {
            Part childPart = partRepository.findById(dto.getChildPartId()).orElseThrow(() -> new PartNotFoundException("Child part with id " + dto.getChildPartId() + " not found!"));
            partRelationship.setChildPart(childPart);
        }

        if (dto.getParentPartId() != null) {
            Part parentPart = partRepository.findById(dto.getParentPartId()).orElseThrow(() -> new PartNotFoundException("PArent part with id " + dto.getParentPartId() + " not found!"));
            partRelationship.setParentPart(parentPart);
        }

        if (dto.getType() != null) {
            partRelationship.setType(dto.getType());
        }

        return PartRelationshipMapper.convertToDefaultDto(partRelationshipRepository.save(partRelationship));
    }

    @Override
    public PartRelationshipDefaultDTO createPartRelationship(PartRelationshipCreateDTO dto) {
        Part childPart = partRepository.findById(dto.getChildPartId()).orElseThrow(() -> new PartNotFoundException("Child part with id " + dto.getChildPartId() + " not found!"));
        Part parentPart = partRepository.findById(dto.getParentPartId()).orElseThrow(() -> new PartNotFoundException("PArent part with id " + dto.getParentPartId() + " not found!"));

        PartRelationship partRelationship = new PartRelationship();
        partRelationship.setChildPart(childPart);
        partRelationship.setParentPart(parentPart);
        partRelationship.setType(dto.getType());

        return PartRelationshipMapper.convertToDefaultDto(partRelationshipRepository.save(partRelationship));
    }

    @Override
    public void deletePartRelationshipById(Long id) {
        partRelationshipRepository.findById(id).orElseThrow(() -> new PartRelationshipNotFoundException("Part relationship with id " + id + " not found!"));
        partRelationshipRepository.deleteById(id);
    }
}
