package ru.brickly.core.service;

import ru.brickly.core.dto.OrderItemDefaultDTO;
import ru.brickly.core.dto.OrderItemUpdateDTO;
import ru.brickly.core.dto.OrderItemWithOrderDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemWithOrderDTO> getOrderItemsBySellerId(long id);

    OrderItemDefaultDTO updateOrderItemById(long id, OrderItemUpdateDTO dto);
}
