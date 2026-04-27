package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.InventorySetDefaultDTO;

public interface InventorySetService {
    Page<InventorySetDefaultDTO> getInventorySetsByInventoryIdPaginated(Integer inventoryId, Pageable pageable);
}
