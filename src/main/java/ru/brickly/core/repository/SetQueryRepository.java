package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.brickly.core.dto.SetContainingBLMinifigDTO;
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
   \s
    JOIN theme t ON s.theme_id = t.id
   \s
    JOIN (
        SELECT DISTINCT ON (set_num)
            set_num,
            id AS inventory_id
        FROM inventory
        ORDER BY set_num, version DESC
    ) latest_inv ON latest_inv.set_num = s.set_num
   \s
    JOIN inventory_part ip ON ip.inventory_id = latest_inv.inventory_id

    JOIN bl_part blp ON blp.rb_id = ip.part_num
                    AND blp.id = :partId
   \s
    WHERE (:colorId IS NULL OR ip.color_id = :colorId)
      AND s.set_num NOT LIKE 'fig-%'
   \s
    GROUP BY\s
        s.set_num,
        s.name,
        s.year,
        s.num_parts,
        s.img_url,
        t.id,
        t.name
   \s""",
            countQuery = """
        SELECT COUNT(DISTINCT s.set_num)

        FROM set s

        JOIN (
            SELECT DISTINCT ON (set_num)
                set_num,
                id AS inventory_id
            FROM inventory
            ORDER BY set_num, version DESC
        ) latest_inv ON latest_inv.set_num = s.set_num

        JOIN inventory_part ip ON ip.inventory_id = latest_inv.inventory_id

        JOIN bl_part blp ON blp.rb_id = ip.part_num
                        AND blp.id = :partId

        WHERE (:colorId IS NULL OR ip.color_id = :colorId)
          AND s.set_num NOT LIKE 'fig-%'
    """,
            nativeQuery = true)
    Page<SetContainingPartDTO> findSetsContainingBLPart(
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

        t.id AS themeId,
        t.name AS themeName,

        CAST(SUM(im.quantity) AS INTEGER) AS minifigQuantity

    FROM set s

    JOIN theme t ON s.theme_id = t.id

    JOIN (
        SELECT DISTINCT ON (set_num)
            set_num,
            id AS inventory_id
        FROM inventory
        ORDER BY set_num, version DESC
    ) latest_inv ON latest_inv.set_num = s.set_num

    JOIN inventory_minifig im ON im.inventory_id = latest_inv.inventory_id

    JOIN bl_minifig bl ON bl.rb_id = im.fig_num
                      AND bl.id = :blMinifigId

    WHERE s.set_num NOT LIKE 'fig-%'

    GROUP BY\s
        s.set_num,
        s.name,
        s.year,
        s.num_parts,
        s.img_url,
        t.id,
        t.name
   \s""",
            countQuery = """
    SELECT COUNT(DISTINCT s.set_num)
    FROM set s

    JOIN (
        SELECT DISTINCT ON (set_num)
            set_num,
            id AS inventory_id
        FROM inventory
        ORDER BY set_num, version DESC
    ) latest_inv ON latest_inv.set_num = s.set_num

    JOIN inventory_minifig im ON im.inventory_id = latest_inv.inventory_id

    JOIN bl_minifig bl ON bl.rb_id = im.fig_num
                      AND bl.id = :blMinifigId

    WHERE s.set_num NOT LIKE 'fig-%'
    """,
            nativeQuery = true)
    Page<SetContainingBLMinifigDTO> findSetsContainingBLMinifig(
            @Param("blMinifigId") String blMinifigId,
            Pageable pageable);;
}
