package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.OrderItemDefaultDTO;
import ru.brickly.core.dto.OrderItemWithOrderDTO;
import ru.brickly.core.entity.OrderItem;

@UtilityClass
public class OrderItemMapper {
    public OrderItemDefaultDTO convertToDefaultDto(OrderItem orderItem) {
        OrderItemDefaultDTO orderItemDefaultDTO = new OrderItemDefaultDTO();
        orderItemDefaultDTO.setId(orderItem.getId());
        orderItemDefaultDTO.setOrderId(orderItem.getOrder().getId());
        orderItemDefaultDTO.setPrice(orderItem.getPrice());
        orderItemDefaultDTO.setStatus(orderItem.getStatus());
        orderItemDefaultDTO.setQuantity(orderItem.getQuantity());
        orderItemDefaultDTO.setListingId(orderItem.getListing().getId());
        return orderItemDefaultDTO;
    }

    public OrderItemWithOrderDTO convertToWithOrderDto(OrderItem orderItem) {
        OrderItemWithOrderDTO orderItemWithOrderDTO = new OrderItemWithOrderDTO();
        orderItemWithOrderDTO.setId(orderItem.getId());
        orderItemWithOrderDTO.setOrder(OrderMapper.convertToShortDto(orderItem.getOrder()));
        orderItemWithOrderDTO.setQuantity(orderItem.getQuantity());
        orderItemWithOrderDTO.setPrice(orderItem.getPrice());
        orderItemWithOrderDTO.setStatus(orderItem.getStatus());
        orderItemWithOrderDTO.setListingId(orderItem.getListing().getId());
        return orderItemWithOrderDTO;
    }
}
