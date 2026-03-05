package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.ElementCreateDTO;
import ru.brickly.core.dto.ElementDefaultDTO;
import ru.brickly.core.dto.ElementUpdateDTO;

public interface ElementService {
    Page<ElementDefaultDTO> getElementsPaginated(Pageable pageable);

    Page<ElementDefaultDTO> getElementsByColorIdPaginated(Integer colorId, Pageable pageable);

    Page<ElementDefaultDTO> getElementsByPartIdPaginated(String partId, Pageable pageable);

    ElementDefaultDTO getElementById(String id);

    ElementDefaultDTO createElement(ElementCreateDTO dto);

    ElementDefaultDTO updateElement(String id, ElementUpdateDTO dto);

    void deleteElementById(String id);
}
