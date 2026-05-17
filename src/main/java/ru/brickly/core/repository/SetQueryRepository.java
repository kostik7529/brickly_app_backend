package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.brickly.core.dto.SetContainingMinifigDTO;
import ru.brickly.core.dto.SetContainingPartDTO;

public interface SetQueryRepository {

    @Query(value = """
    SELECT\s
        s.set_num AS id,
        s.name,
        s.year,
        s.num_parts AS numParts,
        s.img_url AS imageUrl,
       \s
        t.id AS themeId,
        t.name AS themeName,
       \s
        CAST(SUM(ip.quantity) AS INTEGER) AS partQuantity
       \s
    FROM set s
    JOIN theme t ON s.theme_id = t.id
   \s
    JOIN (
        SELECT DISTINCT ON (set_num)\s
            set_num,
            id AS inventory_id
        FROM inventory
        ORDER BY set_num, version DESC
    ) latest_inv ON latest_inv.set_num = s.set_num
   \s
    JOIN inventory_part ip ON ip.inventory_id = latest_inv.inventory_id
                          AND ip.part_num = :partId
                          AND (:colorId IS NULL OR ip.color_id = :colorId)
   \s
    WHERE s.set_num NOT LIKE 'fig-%'
   \s
    GROUP BY s.set_num, s.name, s.year, s.num_parts, s.img_url,\s
             t.id, t.name
   \s""",
            countQuery = """
        SELECT COUNT(DISTINCT s.set_num)
        FROM set s
        JOIN (
            SELECT DISTINCT ON (set_num)\s
                set_num,
                id AS inventory_id
            FROM inventory
            ORDER BY set_num, version DESC
        ) latest_inv ON latest_inv.set_num = s.set_num
        JOIN inventory_part ip ON ip.inventory_id = latest_inv.inventory_id
        WHERE ip.part_num = :partId
          AND (:colorId IS NULL OR ip.color_id = :colorId)
          AND s.set_num NOT LIKE 'fig-%'
   \s""",
            nativeQuery = true)
    Page<SetContainingPartDTO> findSetsContainingPart(
            @Param("partId") String partId,
            @Param("colorId") Integer colorId,
            Pageable pageable);

    @Query(value = """
    SELECT\s
        s.set_num AS id,
        s.name,
        s.year,
        s.num_parts AS numParts,
        s.img_url AS imageUrl,
       \s
        t.id AS themeId,
        t.name AS themeName,
       \s
        CAST(SUM(im.quantity) AS INTEGER) AS minifigQuantity
       \s
    FROM set s
    JOIN theme t ON s.theme_id = t.id
   \s
    JOIN (
        SELECT DISTINCT ON (set_num)\s
            set_num,
            id AS inventory_id
        FROM inventory
        ORDER BY set_num, version DESC
    ) latest_inv ON latest_inv.set_num = s.set_num
   \s
    JOIN inventory_minifig im ON im.inventory_id = latest_inv.inventory_id
                             AND im.fig_num = :minifigId
   \s
    WHERE s.set_num NOT LIKE 'fig-%'
   \s
    GROUP BY s.set_num, s.name, s.year, s.num_parts, s.img_url,\s
             t.id, t.name
   \s""",
            countQuery = """
        SELECT COUNT(DISTINCT s.set_num)
        FROM set s
        JOIN (
            SELECT DISTINCT ON (set_num)\s
                set_num,
                id AS inventory_id
            FROM inventory
            ORDER BY set_num, version DESC
        ) latest_inv ON latest_inv.set_num = s.set_num
        JOIN inventory_minifig im ON im.inventory_id = latest_inv.inventory_id
        WHERE im.fig_num = :minifigId
          AND s.set_num NOT LIKE 'fig-%'
   \s""",
            nativeQuery = true)
    Page<SetContainingMinifigDTO> findSetsContainingMinifig(
            @Param("minifigId") String minifigId,
            Pageable pageable);
}
