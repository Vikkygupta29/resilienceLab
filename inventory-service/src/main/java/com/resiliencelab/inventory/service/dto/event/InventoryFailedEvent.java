package com.resiliencelab.inventory.service.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryFailedEvent {

    private String orderId;
    private String productId;
    private int quantity;
    private String reason;
}
