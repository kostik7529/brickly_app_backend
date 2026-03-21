package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.ThemeCreateDTO;
import ru.brickly.core.dto.ThemeDefaultDTO;
import ru.brickly.core.dto.ThemeUpdateDTO;
import ru.brickly.core.entity.Theme;
import ru.brickly.core.exception.ThemeIdAlreadyExistsException;
import ru.brickly.core.exception.ThemeNotFoundException;
import ru.brickly.core.repository.ThemeRepository;
import ru.brickly.core.service.ThemeService;
import ru.brickly.core.util.ThemeMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;

    @Override
    public Page<ThemeDefaultDTO> getThemesPaginated(Pageable pageable) {
        return themeRepository.findAll(pageable).map(ThemeMapper::convertToDefaultDto);
    }

    @Override
    public Page<ThemeDefaultDTO> getRootThemesPaginated(Pageable pageable) {
        return themeRepository.findByParentIsNull(pageable).map(ThemeMapper::convertToDefaultDto);
    }

    @Override
    public Page<ThemeDefaultDTO> getChildThemesByParentIdPaginated(Integer parentId, Pageable pageable) {
        themeRepository.findById(parentId).orElseThrow(() -> new ThemeNotFoundException("Parent theme with id " + parentId + " not found!"));
        return themeRepository.findByParentId(parentId, pageable).map(ThemeMapper::convertToDefaultDto);
    }

    @Override
    public ThemeDefaultDTO getThemeById(Integer id) {
        return ThemeMapper.convertToDefaultDto(themeRepository.findById(id).orElseThrow(() -> new ThemeNotFoundException("Theme with id " + id + " not found!")));
    }

    @Override
    public ThemeDefaultDTO createTheme(ThemeCreateDTO dto) {
        Theme theme = new Theme();
        if (dto.getId() != null) {
            Optional<Theme> themeExistenceCheck = themeRepository.findById(dto.getId());
            if (themeExistenceCheck.isPresent()) {
                throw new ThemeIdAlreadyExistsException("Theme with id " + dto.getId() + " already exists!");
            }
            theme.setId(dto.getId());
        }

        theme.setName(dto.getName());
        if (dto.getParentId() != null) {
            Theme parent = themeRepository.findById(dto.getParentId()).orElseThrow(() -> new ThemeNotFoundException("Parent theme with id " + dto.getParentId() + " not found!"));
            theme.setParent(parent);
        }

        return ThemeMapper.convertToDefaultDto(themeRepository.save(theme));
    }

    @Override
    public ThemeDefaultDTO updateTheme(Integer id, ThemeUpdateDTO dto) {
        Theme theme = themeRepository.findById(id).orElseThrow(() -> new ThemeNotFoundException("Theme with id " + id + " not found"));

        if (dto.getName() != null) {
            theme.setName(dto.getName());
        }

        if (dto.getParentId() != null) {
            if (dto.getParentId().equals(id)) {
                throw new IllegalArgumentException("Theme cannot be parent of itself!");
            }
            Theme parent = themeRepository.findById(dto.getParentId()).orElseThrow(() -> new ThemeNotFoundException("Parent theme with id  " + dto.getParentId() + " not found!"));
            theme.setParent(parent);
        } else {
            theme.setParent(null);
        }

        return ThemeMapper.convertToDefaultDto((themeRepository.save(theme)));
    }

    @Override
    public void deleteThemeById(Integer id) {
        themeRepository.findById(id).orElseThrow(() -> new ThemeNotFoundException("Theme with id " + id + " not found!"));
        Page<Theme> childThemes = themeRepository.findByParentId(id, Pageable.unpaged());
        if (childThemes.hasContent()) {
            throw new IllegalArgumentException("Cannot delete theme with id"  + id + " because it has child themes!");
        }
        themeRepository.deleteById(id);
    }

    @Override
    public Page<ThemeDefaultDTO> getThemesByNameContainingPaginated(Pageable pageable, String name) {
        return themeRepository.findByNameContainingIgnoreCase(name, pageable).map(ThemeMapper::convertToDefaultDto);
    }
}







