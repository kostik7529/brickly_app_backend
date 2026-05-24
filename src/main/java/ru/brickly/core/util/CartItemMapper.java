package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.CartItemDefaultDTO;
import ru.brickly.core.entity.CartItem;

@UtilityClass
public class CartItemMapper {
    public CartItemDefaultDTO convertToDefaultDto(CartItem cartItem) {
        CartItemDefaultDTO cartItemDefaultDTO = new CartItemDefaultDTO();
        cartItemDefaultDTO.setId(cartItem.getId());
        cartItemDefaultDTO.setUserId(cartItem.getUser().getId());
        cartItemDefaultDTO.setItemType(cartItem.getItemType());
        cartItemDefaultDTO.setQuantity(cartItem.getQuantity());
        cartItemDefaultDTO.setItemId(cartItem.getItemId());
        return cartItemDefaultDTO;
    }
}
