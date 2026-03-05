package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.MinifigDefaultDTO;
import ru.brickly.core.entity.Minifig;

@UtilityClass
public class MinifigMapper {
    public MinifigDefaultDTO convertToDefaultDTO(Minifig minifig) {
        MinifigDefaultDTO minifigDefaultDTO = new MinifigDefaultDTO();
        minifigDefaultDTO.setId(minifig.getId());
        minifigDefaultDTO.setName(minifig.getName());
        minifigDefaultDTO.setImageUrl(minifig.getImageUrl());
        minifigDefaultDTO.setNumParts(minifig.getNumParts());
        return minifigDefaultDTO;
    }
}
