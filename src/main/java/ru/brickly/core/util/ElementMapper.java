package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.ElementDefaultDTO;
import ru.brickly.core.entity.Element;

@UtilityClass
public class ElementMapper {
    public ElementDefaultDTO convertToDefaultDto(Element element) {
        ElementDefaultDTO elementDefaultDTO = new ElementDefaultDTO();
        elementDefaultDTO.setId(element.getId());
        elementDefaultDTO.setPart(PartMapper.convertToDefaultDto(element.getPart()));
        elementDefaultDTO.setColor(ColorMapper.convertToShortDto(element.getColor()));
        elementDefaultDTO.setDesignId(elementDefaultDTO.getDesignId());
        return elementDefaultDTO;
    }
}
