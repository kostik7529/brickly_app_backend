package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.ColorCreateDTO;
import ru.brickly.core.dto.ColorDefaultDTO;
import ru.brickly.core.dto.ColorShortDTO;
import ru.brickly.core.dto.ColorUpdateDTO;

import java.util.List;

public interface ColorService {
    List<ColorDefaultDTO> getAllColors();

    List<ColorShortDTO> getAllShortColors();

    Page<ColorDefaultDTO> getColorsPaginated(Pageable pageable);

    Page<ColorShortDTO> getShortColorsPaginated(Pageable pageable);

    ColorDefaultDTO updateColor(Integer id, ColorUpdateDTO dto);

    ColorDefaultDTO createColor(ColorCreateDTO dto);

    void deleteColorById(Integer id);
}
