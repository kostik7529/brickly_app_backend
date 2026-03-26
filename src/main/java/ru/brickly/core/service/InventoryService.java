package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.InventoryCreateDTO;
import ru.brickly.core.dto.InventoryDefaultDTO;
import ru.brickly.core.dto.InventoryUpdateDTO;

import java.util.List;

public interface InventoryService {
    Page<InventoryDefaultDTO> getInventoriesPaginated(Pageable pageable);

    List<InventoryDefaultDTO> getAllInventoriesByOwnerId(String ownerId);

    InventoryDefaultDTO getInventoryById(Integer id);

    InventoryDefaultDTO updateInventory(Integer id, InventoryUpdateDTO dto);

    InventoryDefaultDTO createInventory(InventoryCreateDTO dto);

    void deleteInventoryById(Integer id);
}
