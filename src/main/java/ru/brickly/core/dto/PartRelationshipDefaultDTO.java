package ru.brickly.core.dto;

import lombok.Data;
import ru.brickly.core.entity.Part;

@Data
public class PartRelationshipDefaultDTO {
    private String type;
    private PartDefaultDTO childPart;
    private PartDefaultDTO parentPart;
}
