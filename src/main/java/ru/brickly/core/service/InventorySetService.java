package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.InventorySetDefaultDTO;
import ru.brickly.core.dto.SetContainingBLMinifigDTO;
import ru.brickly.core.dto.SetContainingBlPartDTO;

public interface InventorySetService {
    Page<InventorySetDefaultDTO> getInventorySetsByInventoryIdPaginated(Integer inventoryId, Pageable pageable);

    Page<SetContainingBlPartDTO> getSetsContainingPartPaginated(String partId, Integer colorId, Pageable pageable);

    Page<SetContainingBLMinifigDTO> getSetsContainingMinifigPaginated(String minifigId, Pageable pageable);
}
