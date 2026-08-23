package com.resiliencelab.inventory.service.service;

import com.resiliencelab.inventory.service.dto.InventoryResponse;

public interface InventoryService {

    InventoryResponse getInventoryById(String productId);
}
