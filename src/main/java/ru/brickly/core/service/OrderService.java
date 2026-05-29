package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.OrderCreateDTO;
import ru.brickly.core.dto.OrderDefaultDTO;
import ru.brickly.core.dto.OrderUpdateDTO;

public interface OrderService {
    OrderDefaultDTO getOrderById(long id);
    Page<OrderDefaultDTO> getOrdersByUserIdPaginated(long userId, Pageable pageable);

    OrderDefaultDTO updateOrderById(long id, OrderUpdateDTO dto);

    OrderDefaultDTO createOrder(OrderCreateDTO dto);
}
