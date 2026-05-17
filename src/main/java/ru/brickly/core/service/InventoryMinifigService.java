package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.InventoryMinifigDefaultDTO;
import ru.brickly.core.dto.MinifigContainingPartDTO;
import ru.brickly.core.dto.MinifigFromSetDTO;

import java.util.List;

public interface InventoryMinifigService {
    Page<InventoryMinifigDefaultDTO> getInventoryMinifigsByInventoryIdPaginated(Integer inventoryId, Pageable pageable);

    Page<MinifigContainingPartDTO> getMinifigsContainingPartPaginated(String partId, Integer colorId, Pageable pageable);

    List<MinifigFromSetDTO> getAllMinifigsFromSet(String setId);
}
