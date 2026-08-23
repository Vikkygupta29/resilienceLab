package com.resiliencelab.inventory.service.service;

import com.resiliencelab.inventory.service.dto.InventoryResponse;
import com.resiliencelab.inventory.service.dto.ReserveInventoryRequest;

public interface InventoryService {

    InventoryResponse getInventoryById(String productId);
    InventoryResponse reserveInventory(String productId, ReserveInventoryRequest request);
}
