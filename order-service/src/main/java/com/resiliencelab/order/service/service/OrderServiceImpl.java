package com.resiliencelab.order.service.service;


import com.resiliencelab.order.service.client.InventoryClient;
import com.resiliencelab.order.service.client.PaymentClient;
import com.resiliencelab.order.service.dto.OrderRequest;
import com.resiliencelab.order.service.dto.OrderResponse;
import com.resiliencelab.order.service.entity.Order;
import com.resiliencelab.order.service.enums.OrderStatus;
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
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;


    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.create(
                request.productId(),
                request.quantity(),
                request.amount()
        );

    Order savedOrder = orderRepository.save(order);

    try{

        // reserve inventory
        inventoryClient.reserveInventory(
                savedOrder.getProductId(),
                savedOrder.getQuantity()
        );
        savedOrder.setStatus(OrderStatus.INVENTORY_RESERVED);
        orderRepository.save(savedOrder);

        // 3. Process payment
        savedOrder.setStatus(OrderStatus.PAYMENT_PROCESSING);
        orderRepository.save(savedOrder);

        paymentClient.processPayment(
                savedOrder.getId(),
                savedOrder.getAmount()
        );

        // 4. Confirm order
        savedOrder.setStatus(OrderStatus.CONFIRMED);

        Order confirmedOrder = orderRepository.save(savedOrder);

        return OrderResponse.from(confirmedOrder);

    } catch (Exception e) {
        e.printStackTrace();

        savedOrder.setStatus(OrderStatus.FAILED);
        orderRepository.save(savedOrder);

        throw new ResponseStatusException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Unable to complete order", e
        );

    }

    }

    @Override
    public OrderResponse getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"order not found")
        );

        return OrderResponse.from(order);
    }

}
