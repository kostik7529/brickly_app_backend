package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.InventorySetDefaultDTO;
import ru.brickly.core.repository.InventorySetRepository;
import ru.brickly.core.service.InventorySetService;
import ru.brickly.core.util.InventorySetMapper;

@Service
@RequiredArgsConstructor
public class InventorySetServiceImpl implements InventorySetService {
    private final InventorySetRepository inventorySetRepository;

    @Override
    public Page<InventorySetDefaultDTO> getInventorySetsByInventoryIdPaginated(Integer inventoryId, Pageable pageable) {
        return inventorySetRepository.findByInventory_Id(inventoryId, pageable).map(InventorySetMapper::convertToDefaultDto);
    }
}
