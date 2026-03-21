package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.*;

public interface PartRelationshipService {
    Page<PartRelationshipDefaultDTO> getAllPartRelationshipsPaginated(Pageable pageable);

    Page<PartAsParentRelationshipDTO> getAllRelationshipsByParentIdPaginated(String parentPartId, Pageable pageable);

    Page<PartAsChildRelationshipDTO> getAllRelationshipsByChildIdPaginated(String childPartId, Pageable pageable);

    PartRelationshipDefaultDTO updatePartRelationship(Long id, PartRelationshipUpdateDTO dto);

    PartRelationshipDefaultDTO createPartRelationship(PartRelationshipCreateDTO dto);

    void deletePartRelationshipById(Long id);
}
