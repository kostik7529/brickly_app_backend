package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.brickly.core.dto.PartFromItemDTO;

public interface PartQueryRepository {

    @Query(value = """
    SELECT\s
        p.part_num AS id,
        p.name AS name,

        p.part_cat_id AS categoryId,
        pc.name AS categoryName,

        ip.color_id AS colorId,
        c.name AS colorName,
        c.rgb AS colorRgb,

        ip.img_url AS imageUrl,

        ip.quantity

    FROM (
        SELECT id
        FROM inventory
        WHERE set_num = :itemId
        ORDER BY version DESC
        LIMIT 1
    ) latest_inv

    JOIN inventory_part ip ON ip.inventory_id = latest_inv.id
    JOIN part p ON p.part_num = ip.part_num
    JOIN color c ON c.id = ip.color_id
    JOIN part_category pc ON pc.id = p.part_cat_id

    ORDER BY c.id, p.name
   \s""",
            nativeQuery = true)
    Page<PartFromItemDTO> findPartsByItemId(
            @Param("itemId") String itemId,
            Pageable pageable);
}
