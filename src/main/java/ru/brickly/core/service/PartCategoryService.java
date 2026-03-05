package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.PartCategoryCreateDTO;
import ru.brickly.core.dto.PartCategoryDefaultDTO;
import ru.brickly.core.dto.PartCategoryUpdateDTO;

import java.util.List;

public interface PartCategoryService {
    PartCategoryDefaultDTO getPartCategoryById(Integer id);

    List<PartCategoryDefaultDTO> getAllPartCategories();

    Page<PartCategoryDefaultDTO> getPartCategoriesPaginated(Pageable pageable);

    PartCategoryDefaultDTO createPartCategory(PartCategoryCreateDTO dto);

    PartCategoryDefaultDTO updatePartCategory(Integer id, PartCategoryUpdateDTO dto);

    void deletePartCategoryById(Integer id);
}
