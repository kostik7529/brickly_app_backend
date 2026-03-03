package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.ColorCreateDTO;
import ru.brickly.core.dto.ColorDefaultDTO;
import ru.brickly.core.dto.ColorShortDTO;
import ru.brickly.core.dto.ColorUpdateDTO;
import ru.brickly.core.entity.Color;
import ru.brickly.core.exception.ColorNotFoundException;
import ru.brickly.core.repository.ColorRepository;
import ru.brickly.core.service.ColorService;
import ru.brickly.core.util.ColorMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public List<ColorDefaultDTO> getAllColors() {
        return colorRepository.findAll().stream().map(ColorMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public List<ColorShortDTO> getAllShortColors() {
        return colorRepository.findAll().stream().map(ColorMapper::convertToShortDto).collect(Collectors.toList());
    }

    @Override
    public Page<ColorDefaultDTO> getColorsPaginated(Pageable pageable) {
        return colorRepository.findAll(pageable).map(ColorMapper::convertToDefaultDto);
    }

    @Override
    public Page<ColorShortDTO> getShortColorsPaginated(Pageable pageable) {
        return colorRepository.findAll(pageable).map(ColorMapper::convertToShortDto);
    }

    @Override
    public ColorDefaultDTO updateColor(Integer id, ColorUpdateDTO dto) {
        Color color = colorRepository.findById(id).orElseThrow(() -> new ColorNotFoundException("Color with id " + id + " not found!"));
        color.setName(dto.getName());
        color.setRgb(dto.getRgb());
        color.setTransparent(dto.isTransparent());
        color.setNumParts(dto.getNumParts());
        color.setNumSets(dto.getNumSets());
        color.setYearSince(dto.getYearSince());
        color.setYearTill(dto.getYearTill());
        return ColorMapper.convertToDefaultDto(colorRepository.save(color));
    }

    @Override
    public ColorDefaultDTO createColor(ColorCreateDTO dto) {
        Color color = new Color();
        color.setName(dto.getName());
        color.setRgb(dto.getRgb());
        color.setTransparent(dto.isTransparent());
        color.setNumParts(dto.getNumParts());
        color.setNumSets(dto.getNumSets());
        color.setYearSince(dto.getYearSince());
        color.setYearTill(dto.getYearTill());
        return ColorMapper.convertToDefaultDto(colorRepository.save(color));
    }

    @Override
    public void deleteColorById(Integer id) {
        colorRepository.findById(id).orElseThrow(() -> new ColorNotFoundException("Color with id " + id + " not found!"));
        colorRepository.deleteById(id);
    }
}
