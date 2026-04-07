package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.InventoryPart;

@Repository
public interface InventoryPartRepository extends JpaRepository<InventoryPart, Long> {
    @EntityGraph(attributePaths = {"inventory", "part", "color", "part.category"})
    Page<InventoryPart> findByInventory_Id(Integer id, Pageable pageable);

    Page<InventoryPart> findByPart_IdAndColor_Id(String partId, Long colorId, Pageable pageable);
}
