package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.ColorDefaultDTO;
import ru.brickly.core.dto.ColorShortDTO;
import ru.brickly.core.entity.Color;

@UtilityClass
public class ColorMapper {
    public ColorDefaultDTO convertToDefaultDto(Color color) {
        ColorDefaultDTO colorDefaultDTO = new ColorDefaultDTO();
        colorDefaultDTO.setId(color.getId());
        colorDefaultDTO.setName(color.getName());
        colorDefaultDTO.setRgb(color.getRgb());
        colorDefaultDTO.setTransparent(color.isTransparent());
        colorDefaultDTO.setNumParts(color.getNumParts());
        colorDefaultDTO.setNumSets(color.getNumSets());
        colorDefaultDTO.setYearSince(color.getYearSince());
        colorDefaultDTO.setYearTill(color.getYearTill());
        return colorDefaultDTO;
    }

    public ColorShortDTO convertToShortDto(Color color) {
        ColorShortDTO colorShortDTO = new ColorShortDTO();
        colorShortDTO.setId(color.getId());
        colorShortDTO.setName(color.getName());
        colorShortDTO.setRgb(color.getRgb());
        colorShortDTO.setTransparent(color.isTransparent());
        return colorShortDTO;
    }
}
