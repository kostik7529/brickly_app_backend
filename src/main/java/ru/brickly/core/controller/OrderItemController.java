package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.OrderItemDefaultDTO;
import ru.brickly.core.dto.OrderItemUpdateDTO;
import ru.brickly.core.service.OrderItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/orders/items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderItemDefaultDTO> updateOrderItemById(@PathVariable long id, @RequestBody OrderItemUpdateDTO dto) {
        return ResponseEntity.ok(orderItemService.updateOrderItemById(id, dto));
    }
}
