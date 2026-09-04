package com.resiliencelab.inventory.service.messaging;

import com.resiliencelab.inventory.service.dto.event.InventoryFailedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryFailedEventProducer {

    private final KafkaTemplate<String, InventoryFailedEvent> kafkaTemplate;

    public InventoryFailedEventProducer(
            KafkaTemplate<String, InventoryFailedEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishInventoryFailed(
            InventoryFailedEvent event) {

        kafkaTemplate.send(
                "inventory.failed",
                event.getOrderId(),
                event
        );

        System.out.println("Published inventory.failed event");
    }
}