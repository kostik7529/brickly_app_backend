package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.UserDefaultDTO;
import ru.brickly.core.dto.UserFullDTO;
import ru.brickly.core.dto.UserShortDTO;
import ru.brickly.core.entity.User;

import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {
    public UserDefaultDTO convertToDefaultDto(User user) {
        UserDefaultDTO userDTO = new UserDefaultDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setBalance(user.getBalance());
        userDTO.setCity(user.getCity());
        userDTO.setCartItems(user.getCartItems() != null ? user.getCartItems().stream().map(CartItemMapper::convertToDefaultDto).collect(Collectors.toList()) : null);
        userDTO.setAuthorities(user.getAuthorities().stream().map(AuthorityMapper::convertToShortDto).collect(Collectors.toList()));
        return userDTO;
    }

    public UserFullDTO convertToFullDto(User user) {
        UserFullDTO userDTO = new UserFullDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setBalance(user.getBalance());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setCity(user.getCity());
        userDTO.setAuthorities(user.getAuthorities().stream().map(AuthorityMapper::convertToShortDto).collect(Collectors.toList()));
        return userDTO;
    }

    public UserShortDTO convertToShortDto(User user) {
        UserShortDTO userShortDTO = new UserShortDTO();
        userShortDTO.setId(user.getId());
        userShortDTO.setName(user.getName());
        userShortDTO.setUsername(user.getUsername());
        return userShortDTO;
    }
}
