package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.InventoryPartCreateDTO;
import ru.brickly.core.dto.InventoryPartDefaultDTO;
import ru.brickly.core.entity.Color;
import ru.brickly.core.entity.Inventory;
import ru.brickly.core.entity.InventoryPart;
import ru.brickly.core.entity.Part;
import ru.brickly.core.exception.ColorNotFoundException;
import ru.brickly.core.exception.InventoryNotFoundException;
import ru.brickly.core.exception.PartNotFoundException;
import ru.brickly.core.repository.ColorRepository;
import ru.brickly.core.repository.InventoryPartRepository;
import ru.brickly.core.repository.InventoryRepository;
import ru.brickly.core.repository.PartRepository;
import ru.brickly.core.service.InventoryPartService;
import ru.brickly.core.util.InventoryPartMapper;

@Service
@RequiredArgsConstructor
public class InventoryPartServiceImpl implements InventoryPartService {
    private final InventoryPartRepository inventoryPartRepository;
    private final PartRepository partRepository;
    private final ColorRepository colorRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public Page<InventoryPartDefaultDTO> getPartsByInventoryIdPaginated(Integer id, Pageable pageable) {
        return inventoryPartRepository.findByInventory_Id(id, pageable).map(InventoryPartMapper::convertToDefaultDto);
    }

    @Override
    public InventoryPartDefaultDTO createInventoryPart(InventoryPartCreateDTO dto) {
        Inventory inventory = inventoryRepository.findById(dto.getInventoryId()).orElseThrow(() -> new InventoryNotFoundException("Inventory with id " + dto.getInventoryId() + " not found!"));
        Part part = partRepository.findById(dto.getPartId()).orElseThrow(() -> new PartNotFoundException("Part with id " + dto.getPartId() + " not found!"));
        Color color = colorRepository.findById(dto.getColorId()).orElseThrow(() -> new ColorNotFoundException("Color with id " + dto.getColorId() + " not found!"));

        InventoryPart inventoryPart = new InventoryPart();
        inventoryPart.setPart(part);
        inventoryPart.setColor(color);
        inventoryPart.setQuantity(dto.getQuantity());
        inventoryPart.setSpare(dto.isSpare());
        inventoryPart.setInventory(inventory);

        return InventoryPartMapper.convertToDefaultDto(inventoryPartRepository.save(inventoryPart));
    }
}
