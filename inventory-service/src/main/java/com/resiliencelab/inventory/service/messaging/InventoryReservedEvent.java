package com.resiliencelab.inventory.service.messaging;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReservedEvent {

    private String orderId;
    private String productId;
    private int quantity;
}
