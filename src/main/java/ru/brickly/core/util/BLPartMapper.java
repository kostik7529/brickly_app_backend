package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.BLPartDefaultDTO;
import ru.brickly.core.entity.BLPart;

@UtilityClass
public class BLPartMapper {
    public BLPartDefaultDTO convertToDefaultDto(BLPart blPart) {
        BLPartDefaultDTO blPartDefaultDTO = new BLPartDefaultDTO();
        blPartDefaultDTO.setId(blPart.getId());
        blPartDefaultDTO.setImageUrl(blPart.getImageUrl());
        return blPartDefaultDTO;
    }
}
