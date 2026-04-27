package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.InventorySetDefaultDTO;
import ru.brickly.core.dto.InventorySetFullDTO;
import ru.brickly.core.entity.InventorySet;

@UtilityClass
public class InventorySetMapper {
    public InventorySetDefaultDTO convertToDefaultDto(InventorySet inventorySet) {
        InventorySetDefaultDTO inventorySetDefaultDTO = new InventorySetDefaultDTO();
        inventorySetDefaultDTO.setId(inventorySet.getId());
        inventorySetDefaultDTO.setQuantity(inventorySet.getQuantity());
        inventorySetDefaultDTO.setSet(SetMapper.convertToDefaultDto(inventorySet.getSet()));
        return inventorySetDefaultDTO;
    }

    public InventorySetFullDTO convertToFullDto(InventorySet inventorySet) {
        InventorySetFullDTO inventorySetFullDTO = new InventorySetFullDTO();
        inventorySetFullDTO.setId(inventorySet.getId());
        inventorySetFullDTO.setSetId(inventorySetFullDTO.getSetId());
        inventorySetFullDTO.setQuantity(inventorySet.getQuantity());
        inventorySetFullDTO.setInventoryId(inventorySetFullDTO.getInventoryId());
        return inventorySetFullDTO;
    }
}
