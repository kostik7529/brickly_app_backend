package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.InventoryMinifig;

@Repository
public interface InventoryMinifigRepository extends JpaRepository<InventoryMinifig, Long> {
    @EntityGraph(attributePaths = {"minifig", "inventory"})
    Page<InventoryMinifig> findByInventory_Id(Integer id, Pageable pageable);
}
