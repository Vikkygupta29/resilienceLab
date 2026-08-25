package com.resiliencelab.order.service.client;

import com.resiliencelab.order.service.dto.client.PaymentRequest;
import com.resiliencelab.order.service.dto.client.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentClient {

    private final RestClient restClient;
    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    public PaymentResponse processPayment(UUID orderId, BigDecimal amount) {

        PaymentRequest request = new PaymentRequest(orderId, amount);

        return restClient.post()
                .uri(paymentServiceUrl + "/api/payments")
                .body(request)
                .retrieve()
                .body(PaymentResponse.class);

    }
}
