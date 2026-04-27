package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.InventoryPartDefaultDTO;
import ru.brickly.core.entity.InventoryPart;

@UtilityClass
public class InventoryPartMapper {
    public InventoryPartDefaultDTO convertToDefaultDto(InventoryPart inventoryPart) {
        InventoryPartDefaultDTO inventoryPartDefaultDTO = new InventoryPartDefaultDTO();
        inventoryPartDefaultDTO.setPart(PartMapper.convertToDefaultDto(inventoryPart.getPart()));
        inventoryPartDefaultDTO.setId(inventoryPart.getId());
        inventoryPartDefaultDTO.setColor(ColorMapper.convertToShortDto(inventoryPart.getColor()));
        inventoryPartDefaultDTO.setQuantity(inventoryPart.getQuantity());
        inventoryPartDefaultDTO.setSpare(inventoryPart.isSpare());
        inventoryPartDefaultDTO.setImageUrl(inventoryPartDefaultDTO.getImageUrl());
        return inventoryPartDefaultDTO;
    }
}
