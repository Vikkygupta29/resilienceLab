package com.resiliencelab.order.service.client;

import com.resiliencelab.order.service.dto.client.InventoryResponse;
import com.resiliencelab.order.service.dto.client.ReserveInventoryRequest;
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

    public InventoryResponse reserveInventory(String productId, int quantity){
        System.out.println("productId: "+ productId);

        ReserveInventoryRequest request =
                new ReserveInventoryRequest(quantity);

        return restClient.post()
                .uri(inventoryServiceUrl + "/api/inventory/{productId}/reservations", productId)
                .body(request)
                .retrieve()
                .body(InventoryResponse.class);

    }


}
