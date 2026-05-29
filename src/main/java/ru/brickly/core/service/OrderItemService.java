package ru.brickly.core.service;

import ru.brickly.core.dto.OrderItemDefaultDTO;
import ru.brickly.core.dto.OrderItemUpdateDTO;

public interface OrderItemService {
    OrderItemDefaultDTO updateOrderItemById(long id, OrderItemUpdateDTO dto);
}
