package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.InventoryMinifigDefaultDTO;
import ru.brickly.core.repository.InventoryMinifigRepository;
import ru.brickly.core.service.InventoryMinifigService;
import ru.brickly.core.util.InventoryMinifigMapper;

@Service
@RequiredArgsConstructor
public class InventoryMinifigServiceImpl implements InventoryMinifigService {
    private final InventoryMinifigRepository inventoryMinifigRepository;

    @Override
    public Page<InventoryMinifigDefaultDTO> getInventoryMinifigsByInventoryIdPaginated(Integer inventoryId, Pageable pageable) {
        return inventoryMinifigRepository.findByInventory_Id(inventoryId, pageable).map(InventoryMinifigMapper::convertToDefaultDto);
    }
}
