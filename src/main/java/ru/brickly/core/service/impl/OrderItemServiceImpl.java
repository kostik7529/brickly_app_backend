package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.OrderItemDefaultDTO;
import ru.brickly.core.dto.OrderItemUpdateDTO;
import ru.brickly.core.dto.OrderItemWithOrderDTO;
import ru.brickly.core.entity.OrderItem;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.OrderItemNotFoundException;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.OrderItemRepository;
import ru.brickly.core.repository.UserRepository;
import ru.brickly.core.service.OrderItemService;
import ru.brickly.core.util.OrderItemMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    @Override
    public List<OrderItemWithOrderDTO> getOrderItemsBySellerId(long id) {
        return orderItemRepository.findByListing_Seller_Id(id).stream().map(OrderItemMapper::convertToWithOrderDto).collect(Collectors.toList());
    }

    @Override
    public OrderItemDefaultDTO updateOrderItemById(long id, OrderItemUpdateDTO dto) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new OrderItemNotFoundException("Order item with id " + id + " not found!"));

        if (dto.getStatus() != null) {
            orderItem.setStatus(dto.getStatus());

            User buyer = userRepository.findById(orderItem.getOrder().getUser().getId()).orElseThrow(() -> new UserNotFoundException("Buyer with id " + orderItem.getOrder().getUser().getId() + " not found!"));
            User seller = userRepository.findById(orderItem.getListing().getSeller().getId()).orElseThrow(() -> new UserNotFoundException("Seller with id " + orderItem.getListing().getSeller().getId() + " not found!"));

            switch (dto.getStatus()) {
                case "received":
                    seller.setBalance(seller.getBalance() + orderItem.getPrice() * orderItem.getQuantity());
                    userRepository.save(seller);
                    break;
                    
                case "canceled":
                    buyer.setBalance(buyer.getBalance() + orderItem.getPrice() * orderItem.getQuantity());
                    userRepository.save(buyer);
                    break;
            }
        }
        return OrderItemMapper.convertToDefaultDto(orderItemRepository.save(orderItem));
    }
}
