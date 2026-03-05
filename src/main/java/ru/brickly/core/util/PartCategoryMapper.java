package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.PartCategoryDefaultDTO;
import ru.brickly.core.dto.PartCategoryShortDTO;
import ru.brickly.core.entity.PartCategory;

@UtilityClass
public class PartCategoryMapper {
    public PartCategoryDefaultDTO convertToDefaultDto(PartCategory partCategory) {
        PartCategoryDefaultDTO partCategoryDefaultDTO = new PartCategoryDefaultDTO();
        partCategoryDefaultDTO.setId(partCategory.getId());
        partCategoryDefaultDTO.setName(partCategory.getName());
        return partCategoryDefaultDTO;
    }

    public PartCategoryShortDTO convertToShortDto(PartCategory partCategory) {
        PartCategoryShortDTO partCategoryShortDTO = new PartCategoryShortDTO();
        partCategoryShortDTO.setName(partCategory.getName());
        return partCategoryShortDTO;
    }
}
