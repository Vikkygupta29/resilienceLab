package com.resiliencelab.order.service.messaging;

import com.resiliencelab.order.service.dto.event.PaymentCompletedEvent;
import com.resiliencelab.order.service.entity.Order;
import com.resiliencelab.order.service.enums.OrderStatus;
import com.resiliencelab.order.service.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentCompletedConsumer {

    private final OrderRepository orderRepository;

    public PaymentCompletedConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(
            topics = "payment.completed",
            groupId = "order-service-payment-group",
            containerFactory = "paymentKafkaListenerContainerFactory"
    )
    public void consumePaymentCompleted(
            PaymentCompletedEvent event) {

        System.out.println("=================================");
        System.out.println("Order Service received payment.completed");
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Amount: " + event.getAmount());

        UUID orderId = event.getOrderId();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException(
                        "Order not found: " + orderId
                ));

        order.setStatus(OrderStatus.CONFIRMED);

        orderRepository.save(order);

        System.out.println("Order status updated to CONFIRMED");
        System.out.println("=================================");
    }
}