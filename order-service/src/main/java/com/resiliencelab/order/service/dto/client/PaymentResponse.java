package com.resiliencelab.order.service.dto.client;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentResponse(
        UUID id,
        UUID orderId,
        BigDecimal amount,
        String status,
        Instant createdAt

) {
}
