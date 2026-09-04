package com.resiliencelab.payment.service.messaging;

import com.resiliencelab.payment.service.dto.event.PaymentCompletedEvent;
import com.resiliencelab.payment.service.dto.event.PaymentFailedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventProducer(
            KafkaTemplate<String, Object> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentCompleted(
            PaymentCompletedEvent event) {

        kafkaTemplate.send(
                "payment.completed",
                event.getOrderId().toString(),
                event
        );

        System.out.println("Published payment.completed event");
    }

    public void publishPaymentFailed(
            PaymentFailedEvent event) {

        kafkaTemplate.send(
                "payment.failed",
                event.getOrderId().toString(),
                event
        );

        System.out.println("Published payment.failed event");
    }
}