package com.resiliencelab.inventory.service.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventProducer {

    private final KafkaTemplate<String, InventoryReservedEvent> kafkaTemplate;

    public InventoryEventProducer(
            KafkaTemplate<String, InventoryReservedEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishInventoryReserved(
            InventoryReservedEvent event) {

        kafkaTemplate.send(
                "inventory.reserved",
                event.getOrderId(),
                event
        );

        System.out.println("Published inventory.reserved event");
    }
}