package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.PartDefaultDTO;
import ru.brickly.core.entity.Part;

@UtilityClass
public class PartMapper {
    public PartDefaultDTO convertToDefaultDto(Part part) {
        PartDefaultDTO partDefaultDTO = new PartDefaultDTO();
        partDefaultDTO.setName(part.getName());
        partDefaultDTO.setId(part.getId());
        partDefaultDTO.setCategory(PartCategoryMapper.convertToShortDto(part.getCategory()));
        return partDefaultDTO;
    }


}
