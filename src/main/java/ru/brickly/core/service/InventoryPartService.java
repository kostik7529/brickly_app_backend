package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.InventoryPartCreateDTO;
import ru.brickly.core.dto.InventoryPartDefaultDTO;

public interface InventoryPartService {
    Page<InventoryPartDefaultDTO> getPartsByInventoryId(Integer id, Pageable pageable);

    InventoryPartDefaultDTO createInventoryPart(InventoryPartCreateDTO dto);
}
