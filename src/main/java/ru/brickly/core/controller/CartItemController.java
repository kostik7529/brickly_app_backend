package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.CartItemCreateDTO;
import ru.brickly.core.dto.CartItemDefaultDTO;
import ru.brickly.core.dto.CartItemUpdateDTO;
import ru.brickly.core.service.CartItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/cart_items")
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping("/by_user_id/{userId}")
    public ResponseEntity<List<CartItemDefaultDTO>> getCartItemsByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(cartItemService.getCartItemsByUserId(userId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartItemDefaultDTO> updateCartItemById(@PathVariable long id, @RequestBody CartItemUpdateDTO dto) {
        return ResponseEntity.ok(cartItemService.updateCartItemById(id, dto));
    }

    @PostMapping("/create")
    public ResponseEntity<CartItemDefaultDTO> createCartItem(@RequestBody CartItemCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.createCartItem(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCartItemById(@PathVariable long id) {
        cartItemService.deleteCartItemById(id);
        return ResponseEntity.noContent().build();
    }
}
