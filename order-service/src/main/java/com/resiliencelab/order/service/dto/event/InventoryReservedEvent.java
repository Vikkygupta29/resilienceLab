package com.resiliencelab.order.service.dto.event;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InventoryReservedEvent {

    private String orderId;
    private String productId;
    private int quantity;

}
