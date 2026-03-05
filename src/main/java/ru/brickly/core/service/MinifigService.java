package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.MinifigCreateDTO;
import ru.brickly.core.dto.MinifigDefaultDTO;
import ru.brickly.core.dto.MinifigUpdateDTO;

public interface MinifigService {
    MinifigDefaultDTO getMinifigById(String id);

    Page<MinifigDefaultDTO> getMinifigsPaginated(Pageable pageable);

    Page<MinifigDefaultDTO> getMinifigsByNameContaining(String nameContaining, Pageable pageable);

    MinifigDefaultDTO createMinifig(MinifigCreateDTO dto);

    MinifigDefaultDTO updateMinifig(String id, MinifigUpdateDTO dto);

    void deleteMinifigById(String id);
}
