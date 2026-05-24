package ru.brickly.core.service;

import ru.brickly.core.dto.CartItemCreateDTO;
import ru.brickly.core.dto.CartItemDefaultDTO;
import ru.brickly.core.dto.CartItemUpdateDTO;
import ru.brickly.core.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItemDefaultDTO> getCartItemsByUserId(Long userId);

    CartItemDefaultDTO updateCartItemById(Long id, CartItemUpdateDTO dto);

    CartItemDefaultDTO createCartItem(CartItemCreateDTO dto);

    void deleteCartItemById(Long id);
}
