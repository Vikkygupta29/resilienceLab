package com.resiliencelab.order.service.messaging;

import com.resiliencelab.order.service.dto.event.PaymentFailedEvent;
import com.resiliencelab.order.service.entity.Order;
import com.resiliencelab.order.service.enums.OrderStatus;
import com.resiliencelab.order.service.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentFailedConsumer {

    private final OrderRepository orderRepository;

    public PaymentFailedConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(
            topics = "payment.failed",
            groupId = "order-service-payment-failed-group",
            containerFactory = "paymentFailedKafkaListenerContainerFactory"
    )
    public void consumePaymentFailed(
            PaymentFailedEvent event) {

        System.out.println("=================================");
        System.out.println("Order Service received payment.failed");
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Amount: " + event.getAmount());
        System.out.println("Reason: " + event.getReason());

        UUID orderId = event.getOrderId();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException(
                        "Order not found: " + orderId
                ));

        order.setStatus(OrderStatus.FAILED);

        orderRepository.save(order);

        System.out.println("Order status updated to FAILED");
        System.out.println("=================================");
    }
}
