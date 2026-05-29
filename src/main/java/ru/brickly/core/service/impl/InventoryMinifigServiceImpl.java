package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.InventoryMinifigDefaultDTO;
import ru.brickly.core.dto.MinifigContainingBlPartDTO;
import ru.brickly.core.dto.MinifigFromSetDTO;
import ru.brickly.core.repository.InventoryMinifigRepository;
import ru.brickly.core.repository.MinifigRepository;
import ru.brickly.core.service.InventoryMinifigService;
import ru.brickly.core.util.InventoryMinifigMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryMinifigServiceImpl implements InventoryMinifigService {
    private final InventoryMinifigRepository inventoryMinifigRepository;
    private final MinifigRepository minifigRepository;

    @Override
    public Page<InventoryMinifigDefaultDTO> getInventoryMinifigsByInventoryIdPaginated(Integer inventoryId, Pageable pageable) {
        return inventoryMinifigRepository.findByInventory_Id(inventoryId, pageable).map(InventoryMinifigMapper::convertToDefaultDto);
    }

    @Override
    public Page<MinifigContainingBlPartDTO> getMinifigsContainingPartPaginated(String partId, Integer colorId, Pageable pageable) {
        return minifigRepository.findMinifigsContainingBlPart(partId, colorId, pageable);
    }

    @Override
    public List<MinifigFromSetDTO> getAllMinifigsFromSet(String setId) {
        return minifigRepository.findMinifigsBySetId(setId);
    }
}
