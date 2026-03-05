package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.PartCreateDTO;
import ru.brickly.core.dto.PartDefaultDTO;
import ru.brickly.core.dto.PartUpdateDTO;
import ru.brickly.core.entity.Part;

import java.util.List;

public interface PartService {
    PartDefaultDTO getPartById(String id);

    List<PartDefaultDTO> getAllParts();

    Page<PartDefaultDTO> getPartsPaginated(Pageable pageable);

    List<PartDefaultDTO> getAllPartsWithIdContaining(String idContaining);

    Page<PartDefaultDTO> getPartsWithIdContainingPaginated(Pageable pageable, String idContaining);

    List<PartDefaultDTO> getAllPartsWithNameContaining(String nameContaining);

    Page<PartDefaultDTO> getPartsWithNameContainingPaginated(Pageable pageable, String nameContaining);

    PartDefaultDTO createPart(PartCreateDTO dto);

    PartDefaultDTO updatePart(String id, PartUpdateDTO dto);

    void deletePartById(String id);
}
