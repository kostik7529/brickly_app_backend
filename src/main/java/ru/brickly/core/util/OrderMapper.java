package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.OrderDefaultDTO;
import ru.brickly.core.dto.OrderItemDefaultDTO;
import ru.brickly.core.dto.OrderShortDTO;
import ru.brickly.core.entity.Order;

import java.util.stream.Collectors;

@UtilityClass
public class OrderMapper {
    public OrderDefaultDTO convertToDefaultDto(Order order) {
        OrderDefaultDTO orderDefaultDTO = new OrderDefaultDTO();
        orderDefaultDTO.setId(order.getId());
        orderDefaultDTO.setCreatedAt(order.getCreatedAt());
        orderDefaultDTO.setShippingAddress(order.getShippingAddress());
        orderDefaultDTO.setShippingMethod(order.getShippingMethod());
        orderDefaultDTO.setUser(UserMapper.convertToShortDto(order.getUser()));
        orderDefaultDTO.setOrderItems(order.getOrderItems().stream().map(OrderItemMapper::convertToDefaultDto).collect(Collectors.toList()));
        return orderDefaultDTO;
    }

    public OrderShortDTO convertToShortDto(Order order) {
        OrderShortDTO orderShortDTO = new OrderShortDTO();
        orderShortDTO.setId(order.getId());
        orderShortDTO.setUser(UserMapper.convertToShortDto(order.getUser()));
        orderShortDTO.setCreatedAt(order.getCreatedAt());
        orderShortDTO.setShippingMethod(order.getShippingMethod());
        orderShortDTO.setShippingAddress(order.getShippingAddress());
        return orderShortDTO;
    }
}
