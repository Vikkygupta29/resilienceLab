package com.resiliencelab.order.service.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryFailedEvent {

    private String orderId;
    private String productId;
    private int quantity;
    private String reason;
}
