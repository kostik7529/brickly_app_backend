package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.PartAsChildRelationshipDTO;
import ru.brickly.core.dto.PartAsParentRelationshipDTO;
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

    public PartAsChildRelationshipDTO convertAsChildDto(PartRelationship partRelationship) {
        PartAsChildRelationshipDTO partAsChildRelationshipDTO = new PartAsChildRelationshipDTO();
        partAsChildRelationshipDTO.setType(partRelationship.getType());
        partAsChildRelationshipDTO.setParentPart(PartMapper.convertToDefaultDto(partRelationship.getParentPart()));
        partAsChildRelationshipDTO.setChildPartId(partRelationship.getChildPart().getId());
        return partAsChildRelationshipDTO;
    }

    public PartAsParentRelationshipDTO convertToAsParentDto(PartRelationship partRelationship) {
        PartAsParentRelationshipDTO partAsParentRelationshipDTO = new PartAsParentRelationshipDTO();
        partAsParentRelationshipDTO.setType(partRelationship.getType());
        partAsParentRelationshipDTO.setParentPartId(partRelationship.getParentPart().getId());
        partAsParentRelationshipDTO.setChildPart(PartMapper.convertToDefaultDto(partRelationship.getChildPart()));
        return partAsParentRelationshipDTO;
    }
}
