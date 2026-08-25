package com.resiliencelab.order.service.dto.client;

import java.time.Instant;

public record InventoryResponse(
        String productId,
        int availableQuantity,
        int reservedQuantity,
        Instant updatedAt
) {


}
