package com.resiliencelab.order.service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank(message = "productId is required")
        String productId,
        @Positive(message = "quantity must be greater than zero")
        int quantity,
        @DecimalMin(value = "0.01", message = "amount must be greater than zero")
        BigDecimal amount

) {
}
