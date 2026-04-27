package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.InventorySet;

@Repository
public interface InventorySetRepository extends JpaRepository<InventorySet, Long> {
    @EntityGraph(attributePaths = {"set", "inventory", "set.theme"})
    Page<InventorySet> findByInventory_Id(Integer inventoryId, Pageable pageable);
}
