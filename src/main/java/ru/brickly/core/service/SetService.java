package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.SetCreateDTO;
import ru.brickly.core.dto.SetDefaultDTO;
import ru.brickly.core.dto.SetUpdateDTO;

public interface SetService {
    Page<SetDefaultDTO> getSetsPaginated(Pageable pageable);

    Page<SetDefaultDTO> getSetsByThemeIdPaginated(Integer themeId, Pageable pageable);

    Page<SetDefaultDTO> getSetsByYearPaginated(Integer year, Pageable pageable);

    Page<SetDefaultDTO> getSetsByNameContainingPaginated(String name, Pageable pageable);

    Page<SetDefaultDTO> getSetsByYearBetweenPaginated(Integer startYear, Integer endYear, Pageable pageable);

    SetDefaultDTO getSetById(String setNum);

    SetDefaultDTO createSet(SetCreateDTO dto);

    SetDefaultDTO updateSet(String id, SetUpdateDTO dto);

    void deleteSetById(String id);

}
