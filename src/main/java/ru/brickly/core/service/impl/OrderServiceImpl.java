package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.brickly.core.dto.*;
import ru.brickly.core.entity.Order;
import ru.brickly.core.entity.OrderItem;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.ListingNotFoundException;
import ru.brickly.core.exception.OrderNotFoundException;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.ListingRepository;
import ru.brickly.core.repository.OrderItemRepository;
import ru.brickly.core.repository.OrderRepository;
import ru.brickly.core.repository.UserRepository;
import ru.brickly.core.service.OrderService;
import ru.brickly.core.util.OrderMapper;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;

    @Override
    public OrderDefaultDTO getOrderById(long id) {
        return OrderMapper.convertToDefaultDto(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found!")));
    }

    @Override
    public Page<OrderDefaultDTO> getOrdersByUserIdPaginated(long userId, Pageable pageable) {
        return orderRepository.findByUser_Id(userId, pageable).map(OrderMapper::convertToDefaultDto);
    }

    @Override
    public OrderDefaultDTO updateOrderById(long id, OrderUpdateDTO dto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found!"));

        if (dto.getShippingAddress() != null) {
            order.setShippingAddress(dto.getShippingAddress());
        }

        if (dto.getShippingMethod() != null) {
            order.setShippingMethod(dto.getShippingMethod());
        }

        return OrderMapper.convertToDefaultDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderDefaultDTO createOrder(OrderCreateDTO dto) {
        User buyer = userRepository.findById(dto.getUserId()).orElseThrow(() -> new UserNotFoundException("User with id " + dto.getUserId() + " not found!"));

        Order order = new Order();
        order.setCreatedAt(dto.getCreatedAt());
        order.setShippingAddress(dto.getShippingAddress());
        order.setShippingMethod(dto.getShippingMethod());
        order.setUser(buyer);
        order.setOrderItems(new ArrayList<>());
        Order savedOrder = orderRepository.save(order);

        int totalAmount = 0;
        if (dto.getOrderItems() != null && !dto.getOrderItems().isEmpty()) {
            for (int i = 0; i < dto.getOrderItems().size(); i++) {
                OrderItemCreateDTO orderItemCreateDTO = dto.getOrderItems().get(i);

                OrderItem orderItem = new OrderItem();
                orderItem.setStatus("on_confirmation");
                orderItem.setQuantity(orderItemCreateDTO.getQuantity());
                orderItem.setPrice(orderItemCreateDTO.getPrice());
                orderItem.setOrder(savedOrder);
                orderItem.setListing(listingRepository.findById(orderItemCreateDTO.getListingId()).orElseThrow(() -> new ListingNotFoundException("Listing with id " + orderItemCreateDTO.getListingId() + " not found!")));

                OrderItem savedOrderItem = orderItemRepository.save(orderItem);

                savedOrder.getOrderItems().add(savedOrderItem);

                totalAmount += orderItemCreateDTO.getPrice() * orderItemCreateDTO.getQuantity();
            }
        }
        buyer.setBalance(buyer.getBalance() - totalAmount);
        userRepository.save(buyer);

        return OrderMapper.convertToDefaultDto(savedOrder);
    }

    @Override
    public void deleteOrderById(long id) {
        orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found!"));
        orderRepository.deleteById(id);
    }
}
