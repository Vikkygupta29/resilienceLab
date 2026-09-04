package com.resiliencelab.order.service.messaging;

import com.resiliencelab.order.service.dto.event.PaymentRequestedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private final KafkaTemplate<String, PaymentRequestedEvent> kafkaTemplate;

    public PaymentEventProducer(
            KafkaTemplate<String, PaymentRequestedEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentRequested(
            PaymentRequestedEvent event) {

        kafkaTemplate.send(
                "payment.requested",
                event.getOrderId().toString(),
                event
        );

        System.out.println("Published payment.requested event");
    }
}