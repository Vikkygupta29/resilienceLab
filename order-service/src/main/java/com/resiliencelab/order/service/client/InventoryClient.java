package com.resiliencelab.order.service.client;

import com.resiliencelab.order.service.dto.client.InventoryResponse;
import com.resiliencelab.order.service.dto.client.ReserveInventoryRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestClient restClient;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @CircuitBreaker(
            name = "inventoryService",
            fallbackMethod = "inventoryFallback"
    )
    @Retry(name = "inventoryService")
    public InventoryResponse reserveInventory(String productId, int quantity) {

        ReserveInventoryRequest request =
                new ReserveInventoryRequest(quantity);

        return restClient.post()
                .uri(
                        inventoryServiceUrl +
                                "/api/inventory/{productId}/reservations",
                        productId
                )
                .body(request)
                .retrieve()
                .body(InventoryResponse.class);
    }

    private InventoryResponse inventoryFallback(
            String productId,
            int quantity,
            Throwable throwable) {

        System.out.println(
                "Inventory service unavailable for product: " + productId
        );

        System.out.println(
                throwable.getClass().getSimpleName()
        );

        throw new RuntimeException(
                "Inventory service temporarily unavailable",
                throwable
        );
    }
}