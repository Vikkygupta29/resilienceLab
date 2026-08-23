package com.resiliencelab.order.service.service;


import com.resiliencelab.order.service.dto.OrderRequest;
import com.resiliencelab.order.service.dto.OrderResponse;
import com.resiliencelab.order.service.entity.Order;
import com.resiliencelab.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;


    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.create(
                request.productId(),
                request.quantity(),
                request.amount()
        );

    Order savedOrder = orderRepository.save(order);

       return OrderResponse.from(savedOrder);
    }

    @Override
    public OrderResponse getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"order not found")
        );

        return OrderResponse.from(order);
    }

}
