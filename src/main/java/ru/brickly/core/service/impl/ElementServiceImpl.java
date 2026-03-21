package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.ElementCreateDTO;
import ru.brickly.core.dto.ElementDefaultDTO;
import ru.brickly.core.dto.ElementUpdateDTO;
import ru.brickly.core.entity.Color;
import ru.brickly.core.entity.Element;
import ru.brickly.core.entity.Part;
import ru.brickly.core.exception.ColorNotFoundException;
import ru.brickly.core.exception.ElementIdAlreadyExistsException;
import ru.brickly.core.exception.ElementNotFoundException;
import ru.brickly.core.exception.PartNotFoundException;
import ru.brickly.core.repository.ColorRepository;
import ru.brickly.core.repository.ElementRepository;
import ru.brickly.core.repository.PartRepository;
import ru.brickly.core.service.ElementService;
import ru.brickly.core.util.ElementMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {
    private final ElementRepository elementRepository;
    private final PartRepository partRepository;
    private final ColorRepository colorRepository;

    @Override
    public Page<ElementDefaultDTO> getElementsPaginated(Pageable pageable) {
        return elementRepository.findAll(pageable).map(ElementMapper::convertToDefaultDto);
    }

    @Override
    public Page<ElementDefaultDTO> getElementsByColorIdPaginated(Integer colorId, Pageable pageable) {
        return elementRepository.findByColorId(colorId, pageable).map(ElementMapper::convertToDefaultDto);
    }

    @Override
    public Page<ElementDefaultDTO> getElementsByPartIdPaginated(String partId, Pageable pageable) {
        return elementRepository.findByPartId(partId, pageable).map(ElementMapper::convertToDefaultDto);
    }

    @Override
    public ElementDefaultDTO getElementById(String id) {
        return ElementMapper.convertToDefaultDto(elementRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Element with id " + id + " not found!")));
    }

    @Override
    public ElementDefaultDTO createElement(ElementCreateDTO dto) {
        Optional<Element> elementExistenceCheck = elementRepository.findById(dto.getId());
        if (elementExistenceCheck.isPresent()) {
            throw new ElementIdAlreadyExistsException("Element with id " + dto.getId() + " already exists!");
        }
        Color color = colorRepository.findById(dto.getColorId()).orElseThrow(() -> new ColorNotFoundException("Color with id " + dto.getColorId() + " not found!"));
        Part part = partRepository.findById(dto.getPartId()).orElseThrow(() -> new PartNotFoundException("Part with id " + dto.getPartId() + " not found!"));
        Element element = new Element();
        element.setId(dto.getId());
        element.setPart(part);
        element.setColor(color);
        element.setDesignId(dto.getDesignId());
        return ElementMapper.convertToDefaultDto(elementRepository.save(element));
    }

    @Override
    public ElementDefaultDTO updateElement(String id, ElementUpdateDTO dto) {
        Element element = elementRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Element with id " + id + " not found!"));

        if (dto.getColorId() != null) {
            Color color = colorRepository.findById(dto.getColorId()).orElseThrow(() -> new ColorNotFoundException("Color with id " + dto.getColorId() + " not found!"));
            element.setColor(color);
        }

        if (dto.getPartId() != null) {
            Part part = partRepository.findById(dto.getPartId()).orElseThrow(() -> new PartNotFoundException("Part with id " + dto.getPartId() + " not found!"));
            element.setPart(part);
        }

        if (dto.getDesignId() != null) {
            element.setDesignId(dto.getDesignId());
        }

        return ElementMapper.convertToDefaultDto(elementRepository.save(element));
    }

    @Override
    public void deleteElementById(String id) {
        elementRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Element with id " + id + " not found!"));
        elementRepository.deleteById(id);
    }
}
