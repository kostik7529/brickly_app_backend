package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.InventoryDefaultDTO;
import ru.brickly.core.entity.Inventory;

@UtilityClass
public class InventoryMapper {
    public InventoryDefaultDTO convertToDefaultDto(Inventory inventory) {
        InventoryDefaultDTO inventoryDefaultDTO = new InventoryDefaultDTO();
        inventoryDefaultDTO.setId(inventory.getId());
        inventoryDefaultDTO.setVersion(inventory.getVersion());
        inventoryDefaultDTO.setOwnerId(inventory.getOwnerId());
        return inventoryDefaultDTO;
    }
}
