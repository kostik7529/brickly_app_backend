package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.InventorySetDefaultDTO;
import ru.brickly.core.dto.SetContainingMinifigDTO;
import ru.brickly.core.dto.SetContainingPartDTO;

public interface InventorySetService {
    Page<InventorySetDefaultDTO> getInventorySetsByInventoryIdPaginated(Integer inventoryId, Pageable pageable);

    Page<SetContainingPartDTO> getSetsContainingPartPaginated(String partId, Integer colorId, Pageable pageable);

    Page<SetContainingMinifigDTO> getSetsContainingMinifigPaginated(String minifigId, Pageable pageable);
}
