package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.brickly.core.dto.MinifigContainingPartDTO;
import ru.brickly.core.dto.MinifigFromSetDTO;

import java.util.List;

public interface MinifigQueryRepository {

    @Query(value = """
    SELECT\s
        mf.fig_num AS id,
        mf.name,
        mf.num_parts AS numParts,
        mf.img_url AS imageUrl,

        CAST(SUM(ip.quantity) AS INTEGER) AS partQuantity

    FROM minifig mf

    JOIN (
        SELECT DISTINCT ON (set_num)\s
            set_num,
            id AS inventory_id
        FROM inventory
        WHERE set_num LIKE 'fig-%'
        ORDER BY set_num, version DESC
    ) latest_inv ON latest_inv.set_num = mf.fig_num

    JOIN inventory_part ip ON ip.inventory_id = latest_inv.inventory_id
                          AND ip.part_num = :partId
                          AND (:colorId IS NULL OR ip.color_id = :colorId)

    GROUP BY mf.fig_num, mf.name, mf.num_parts, mf.img_url
   \s""",
            countQuery = """
    SELECT COUNT(DISTINCT mf.fig_num)
    FROM minifig mf
    JOIN (
        SELECT DISTINCT ON (set_num)\s
            set_num,
            id AS inventory_id
        FROM inventory
        WHERE set_num LIKE 'fig-%'
        ORDER BY set_num, version DESC
    ) latest_inv ON latest_inv.set_num = mf.fig_num
    JOIN inventory_part ip ON ip.inventory_id = latest_inv.inventory_id
    WHERE ip.part_num = :partId
      AND (:colorId IS NULL OR ip.color_id = :colorId)
   \s""",
            nativeQuery = true)
    Page<MinifigContainingPartDTO> findMinifigsContainingPart(
            @Param("partId") String partId,
            @Param("colorId") Integer colorId,
            Pageable pageable);

    @Query(value = """
    SELECT\s
        mf.fig_num AS id,
        mf.name,
        mf.num_parts AS numParts,
        mf.img_url AS imageUrl,

        im.quantity AS quantity

    FROM (
        SELECT id
        FROM inventory
        WHERE set_num = :setId
        ORDER BY version DESC
        LIMIT 1
    ) latest_inv

    JOIN inventory_minifig im ON im.inventory_id = latest_inv.id
    JOIN minifig mf ON mf.fig_num = im.fig_num
   \s""",
            nativeQuery = true)
    List<MinifigFromSetDTO> findMinifigsBySetId(@Param("setId") String setId);
}
