package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.ThemeCreateDTO;
import ru.brickly.core.dto.ThemeDefaultDTO;
import ru.brickly.core.dto.ThemeUpdateDTO;

public interface ThemeService {
    Page<ThemeDefaultDTO> getThemesPaginated(Pageable pageable);

    Page<ThemeDefaultDTO> getRootThemesPaginate(Pageable pageable);

    Page<ThemeDefaultDTO> getChildThemesByParentIdPaginated(Integer parentId, Pageable pageable);

    ThemeDefaultDTO getThemeById(Integer id);

    ThemeDefaultDTO createTheme(ThemeCreateDTO dto);

    ThemeDefaultDTO updateTheme(Integer id, ThemeUpdateDTO dto);

    void deleteThemeById(Integer id);

    Page<ThemeDefaultDTO> getThemesByNameContainingPaginated(Pageable pageable, String name);

}