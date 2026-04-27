package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.InventoryMinifigDefaultDTO;

public interface InventoryMinifigService {
    Page<InventoryMinifigDefaultDTO> getInventoryMinifigsByInventoryIdPaginated(Integer inventoryId, Pageable pageable);
}
