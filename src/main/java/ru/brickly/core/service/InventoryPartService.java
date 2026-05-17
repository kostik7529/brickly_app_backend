package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.InventoryPartCreateDTO;
import ru.brickly.core.dto.InventoryPartDefaultDTO;
import ru.brickly.core.dto.PartFromItemDTO;

public interface InventoryPartService {
    Page<InventoryPartDefaultDTO> getPartsByInventoryIdPaginated(Integer id, Pageable pageable);

    InventoryPartDefaultDTO createInventoryPart(InventoryPartCreateDTO dto);

    Page<PartFromItemDTO> getPartsFromItemPaginated(String itemId, Pageable pageable);
}
