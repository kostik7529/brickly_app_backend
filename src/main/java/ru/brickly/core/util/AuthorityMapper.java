package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.AuthorityShortDTO;
import ru.brickly.core.entity.Authority;

@UtilityClass
public class AuthorityMapper {
    public AuthorityShortDTO convertToShortDto(Authority authority) {
        AuthorityShortDTO authorityShortDTO = new AuthorityShortDTO();
        authorityShortDTO.setAuthority(authority.getAuthority());
        return authorityShortDTO;
    }
}
