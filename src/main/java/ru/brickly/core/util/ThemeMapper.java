package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.ThemeDefaultDTO;
import ru.brickly.core.dto.ThemeShortDTO;
import ru.brickly.core.entity.Theme;

@UtilityClass
public class ThemeMapper {
    public ThemeDefaultDTO convertToDefaultDto(Theme theme) {
        ThemeDefaultDTO themeDefaultDTO = new ThemeDefaultDTO();
        themeDefaultDTO.setId(theme.getId());
        themeDefaultDTO.setName(theme.getName());
        if (theme.getParent() != null) {
            themeDefaultDTO.setParent(convertToShortDto(theme.getParent()));
        }
        return themeDefaultDTO;
    }

    public ThemeShortDTO convertToShortDto(Theme theme) {
        ThemeShortDTO themeShortDTO = new ThemeShortDTO();
        themeShortDTO.setId(theme.getId());
        themeShortDTO.setName(theme.getName());
        return themeShortDTO;
    }
}

