package com.resiliencelab.inventory.service.dto;

import jakarta.validation.constraints.Positive;

public record ReserveInventoryRequest(
        @Positive(message = "Quantity must be greater than zero")
        int quantity
) {


}
