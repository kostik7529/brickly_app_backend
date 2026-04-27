package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.InventoryMinifigDefaultDTO;
import ru.brickly.core.dto.InventoryMinifigFullDTO;
import ru.brickly.core.dto.InventoryPartDefaultDTO;
import ru.brickly.core.entity.InventoryMinifig;

@UtilityClass
public class InventoryMinifigMapper {
    public InventoryMinifigDefaultDTO convertToDefaultDto(InventoryMinifig inventoryMinifig) {
        InventoryMinifigDefaultDTO inventoryMinifigDefaultDTO = new InventoryMinifigDefaultDTO();
        inventoryMinifigDefaultDTO.setId(inventoryMinifig.getId());
        inventoryMinifigDefaultDTO.setMinifig(MinifigMapper.convertToDefaultDTO(inventoryMinifig.getMinifig()));
        inventoryMinifigDefaultDTO.setQuantity(inventoryMinifig.getQuantity());
        return inventoryMinifigDefaultDTO;
    }

    public InventoryMinifigFullDTO convertToFullDto(InventoryMinifig inventoryMinifig) {
        InventoryMinifigFullDTO inventoryMinifigFullDTO = new InventoryMinifigFullDTO();
        inventoryMinifigFullDTO.setId(inventoryMinifig.getId());
        inventoryMinifigFullDTO.setMinifigId(inventoryMinifig.getMinifig().getId());
        inventoryMinifigFullDTO.setInventoryId(inventoryMinifig.getInventory().getId());
        inventoryMinifigFullDTO.setQuantity(inventoryMinifig.getQuantity());
        return inventoryMinifigFullDTO;
    }
}
