package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.CartItemCreateDTO;
import ru.brickly.core.dto.CartItemDefaultDTO;
import ru.brickly.core.dto.CartItemUpdateDTO;
import ru.brickly.core.entity.CartItem;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.CartItemNotFoundException;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.CartItemRepository;
import ru.brickly.core.repository.UserRepository;
import ru.brickly.core.service.CartItemService;
import ru.brickly.core.util.CartItemMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Override
    public List<CartItemDefaultDTO> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findCartItemByUserId(userId).stream().map(CartItemMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public CartItemDefaultDTO updateCartItemById(Long id, CartItemUpdateDTO dto) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new CartItemNotFoundException("Cart item with id " + id + " nor found!"));

        if (dto.getQuantity() != null) {
            cartItem.setQuantity(dto.getQuantity());
        }

        return CartItemMapper.convertToDefaultDto(cartItemRepository.save(cartItem));
    }

    @Override
    public CartItemDefaultDTO createCartItem(CartItemCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new UserNotFoundException("User with id " + dto.getUserId() + " not found!"));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setItemId(dto.getItemId());
        cartItem.setItemType(dto.getItemType());
        cartItem.setQuantity(dto.getQuantity());

        return CartItemMapper.convertToDefaultDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void deleteCartItemById(Long id) {
        cartItemRepository.findById(id).orElseThrow(() -> new CartItemNotFoundException("Cart item with id " + id + " nor found!"));
        cartItemRepository.deleteById(id);
    }
}
