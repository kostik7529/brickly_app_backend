package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.OrderCreateDTO;
import ru.brickly.core.dto.OrderDefaultDTO;
import ru.brickly.core.dto.OrderUpdateDTO;
import ru.brickly.core.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/by_id/{id}")
    public ResponseEntity<OrderDefaultDTO> getOrderById(@PathVariable long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/by_user_id/{userId}")
    public ResponseEntity<Page<OrderDefaultDTO>> getOrdersByUserIdPaginated(@PathVariable long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getOrdersByUserIdPaginated(userId, pageable));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDefaultDTO> updateOrderById(@PathVariable long id, @RequestBody OrderUpdateDTO dto) {
        return ResponseEntity.ok(orderService.updateOrderById(id, dto));
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDefaultDTO> createOrder(@RequestBody OrderCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(dto));
    }
}
