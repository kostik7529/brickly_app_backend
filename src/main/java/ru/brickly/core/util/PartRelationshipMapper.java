package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.PartRelationshipDefaultDTO;
import ru.brickly.core.entity.PartRelationship;

@UtilityClass
public class PartRelationshipMapper {
    public PartRelationshipDefaultDTO convertToDefaultDto(PartRelationship partRelationship) {
        PartRelationshipDefaultDTO partRelationshipDefaultDTO = new PartRelationshipDefaultDTO();
        partRelationshipDefaultDTO.setType(partRelationship.getType());
        partRelationshipDefaultDTO.setParentPart(PartMapper.convertToDefaultDto(partRelationship.getParentPart()));
        partRelationshipDefaultDTO.setChildPart(PartMapper.convertToDefaultDto(partRelationship.getChildPart()));
        return partRelationshipDefaultDTO;
    }
}
