package com.resiliencelab.order.service.dto;

import com.resiliencelab.order.service.entity.Order;
import com.resiliencelab.order.service.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderResponse(
        UUID orderId,
        String productId,
        int quantity,
        BigDecimal amount,
        OrderStatus status,
        Instant createdAt
) {

    public static OrderResponse from(Order order){
        return  new OrderResponse(
                order.getId(),
                order.getProductId(),
                order.getQuantity(),
                order.getAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}
