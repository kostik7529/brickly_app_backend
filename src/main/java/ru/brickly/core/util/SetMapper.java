package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.SetDefaultDTO;
import ru.brickly.core.entity.Set;

@UtilityClass
public class SetMapper {
    public SetDefaultDTO convertToDefaultDto(Set set) {
        SetDefaultDTO setDefaultDTO = new SetDefaultDTO();
        setDefaultDTO.setName(set.getName());
        setDefaultDTO.setYear(set.getYear());
        setDefaultDTO.setNumParts(set.getNumParts());
        setDefaultDTO.setId(set.getId());
        setDefaultDTO.setTheme(ThemeMapper.convertToShortDto(set.getTheme()));
        setDefaultDTO.setImageUrl(set.getImageUrl());
        return setDefaultDTO;
    }
}