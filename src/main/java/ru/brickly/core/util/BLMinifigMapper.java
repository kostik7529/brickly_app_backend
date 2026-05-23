package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.BLMinifigDefaultDTO;
import ru.brickly.core.entity.BLMinifig;

@UtilityClass
public class BLMinifigMapper {
    public BLMinifigDefaultDTO convertToDefaultDto(BLMinifig blMinifig) {
        BLMinifigDefaultDTO blMinifigDefaultDTO = new BLMinifigDefaultDTO();
        blMinifigDefaultDTO.setId(blMinifig.getId());
        blMinifigDefaultDTO.setName(blMinifig.getName());
        blMinifigDefaultDTO.setCategoryName(blMinifig.getCategoryName());
        blMinifigDefaultDTO.setImageUrl(blMinifig.getImageUrl());
        blMinifigDefaultDTO.setUrl(blMinifig.getUrl());
        return blMinifigDefaultDTO;
    }
}
