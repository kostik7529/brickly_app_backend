package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.brickly.core.dto.SetCreateDTO;
import ru.brickly.core.dto.SetDefaultDTO;
import ru.brickly.core.dto.SetUpdateDTO;
import ru.brickly.core.entity.Set;
import ru.brickly.core.entity.Theme;
import ru.brickly.core.exception.SetIdAlreadyExistsException;
import ru.brickly.core.exception.SetNotFoundException;
import ru.brickly.core.exception.ThemeNotFoundException;
import ru.brickly.core.repository.SetRepository;
import ru.brickly.core.repository.ThemeRepository;
import ru.brickly.core.service.SetService;
import ru.brickly.core.util.SetMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SetServiceImpl implements SetService {
    private final SetRepository setRepository;
    private final ThemeRepository themeRepository;

    @Override
    public Page<SetDefaultDTO> getSetsPaginated(Pageable pageable) {
        return setRepository.findAll(pageable).map(SetMapper::convertToDefaultDto);
    }

    @Override
    public Page<SetDefaultDTO> getSetsByThemeIdPaginated(Integer themeId, Pageable pageable) {
        themeRepository.findById(themeId).orElseThrow(() -> new ThemeNotFoundException("Theme with id " + themeId + " not found!"));
        return setRepository.findByThemeId(themeId, pageable).map(SetMapper::convertToDefaultDto);
    }

    @Override
    public Page<SetDefaultDTO> getSetsByYearPaginated(Integer year, Pageable pageable) {
        return setRepository.findByYear(year, pageable).map(SetMapper::convertToDefaultDto);
    }

    @Override
    public Page<SetDefaultDTO> getSetsByNameContainingPaginated(String name, Pageable pageable) {
        return setRepository.findByNameContainingIgnoreCase(name, pageable).map(SetMapper::convertToDefaultDto);
    }

    @Override
    public Page<SetDefaultDTO> getSetsByYearBetweenPaginated(Integer startYear, Integer endYear, Pageable pageable) {
        return setRepository.findByYearBetween(startYear, endYear, pageable).map(SetMapper::convertToDefaultDto);
    }

    @Override
    public SetDefaultDTO getSetById(String id) {
        return SetMapper.convertToDefaultDto(setRepository.findById(id).orElseThrow(() -> new SetNotFoundException("Set with number " + id + " not found!"))
        );
    }

    @Override
    public SetDefaultDTO createSet(SetCreateDTO dto) {
        Optional<Set> setExistenceCheck = setRepository.findById(dto.getId());

        if (setExistenceCheck.isPresent()){
            throw new SetIdAlreadyExistsException("Set with number " + dto.getId() + " already exists!");
        }

        Theme theme = themeRepository.findById(dto.getThemeId()).orElseThrow(() -> new ThemeNotFoundException("Theme with id " + dto.getThemeId() + " not found!"));

        Set set = new Set();
        set.setTheme(theme);
        set.setId(dto.getId());
        set.setName(dto.getName());
        set.setYear(dto.getYear());
        set.setNumParts(dto.getNumParts());
        return SetMapper.convertToDefaultDto(setRepository.save(set));
    }

    @Override
    public SetDefaultDTO updateSet(String id, SetUpdateDTO dto) {
        Set set = setRepository.findById(id).orElseThrow(() -> new SetNotFoundException("Set with number " + id + " not found!"));

        if (dto.getName() != null) {
            set.setName(dto.getName());
        }

        if (dto.getYear() != null) {
            set.setYear(dto.getYear());
        }

        if (dto.getNumParts() != null) {
            set.setNumParts(dto.getNumParts());
        }

        return SetMapper.convertToDefaultDto(setRepository.save(set));
    }

    @Override
    public void deleteSetById(String id) {
        setRepository.findById(id).orElseThrow(() -> new SetNotFoundException("Set with number " + id + " not found!"));
        setRepository.deleteById(id);
    }
}