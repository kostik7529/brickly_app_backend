package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.InventoryCreateDTO;
import ru.brickly.core.dto.InventoryDefaultDTO;
import ru.brickly.core.dto.InventoryUpdateDTO;
import ru.brickly.core.entity.Inventory;
import ru.brickly.core.entity.Minifig;
import ru.brickly.core.entity.Set;
import ru.brickly.core.exception.InventoryIdAlreadyExistsException;
import ru.brickly.core.exception.InventoryNotFoundException;
import ru.brickly.core.exception.InventoryUpdateException;
import ru.brickly.core.repository.InventoryRepository;
import ru.brickly.core.repository.MinifigRepository;
import ru.brickly.core.repository.SetRepository;
import ru.brickly.core.service.InventoryService;
import ru.brickly.core.util.InventoryMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final MinifigRepository minifigRepository;
    private final SetRepository setRepository;

    @Override
    public Page<InventoryDefaultDTO> getInventoriesPaginated(Pageable pageable) {
        return inventoryRepository.findAll(pageable).map(InventoryMapper::convertToDefaultDto);
    }

    @Override
    public List<InventoryDefaultDTO> getAllInventoriesByOwnerId(String ownerId) {
        return inventoryRepository.findByOwnerId(ownerId).stream().map(InventoryMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public InventoryDefaultDTO getInventoryById(Integer id) {
        return InventoryMapper.convertToDefaultDto(inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException("Inventory with id " + id + " not found!")));
    }

    @Override
    public InventoryDefaultDTO updateInventory(Integer id, InventoryUpdateDTO dto) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException("Inventory with id " + id + " not found!"));

        if (dto.getVersion() != null) {
            inventory.setVersion(dto.getVersion());
        }

        if (dto.getOwnerId() != null) {
            Optional<Set> optionalSet = setRepository.findById(dto.getOwnerId());
            Optional<Minifig> optionalMinifig = minifigRepository.findById(dto.getOwnerId());

            if (optionalMinifig.isPresent() || optionalSet.isPresent()) {
                inventory.setOwnerId(dto.getOwnerId());
            } else {
                throw new InventoryUpdateException("Minifig or set with id " + dto.getOwnerId() + " not found!");
            }
        }

        return InventoryMapper.convertToDefaultDto(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDefaultDTO createInventory(InventoryCreateDTO dto) {
        Inventory inventory = new Inventory();
        Optional<Inventory> inventoryExistenceCheck = inventoryRepository.findById(dto.getId());

        if (inventoryExistenceCheck.isPresent()) {
            throw new InventoryIdAlreadyExistsException("Inventory wih id " + dto.getId() + " already exists!");
        }

        inventory.setOwnerId(dto.getOwnerId());
        inventory.setVersion(dto.getVersion());

        return InventoryMapper.convertToDefaultDto(inventoryRepository.save(inventory));
    }

    @Override
    public void deleteInventoryById(Integer id) {
        inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException("Inventory with id " + id + " not found!"));
        inventoryRepository.deleteById(id);
    }
}
