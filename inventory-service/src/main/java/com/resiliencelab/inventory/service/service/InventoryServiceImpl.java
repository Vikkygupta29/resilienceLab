package com.resiliencelab.inventory.service.service;

import com.resiliencelab.inventory.service.dto.InventoryResponse;
import com.resiliencelab.inventory.service.entity.Inventory;
import com.resiliencelab.inventory.service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;


    @Override
    public InventoryResponse getInventoryById(String productId) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"product not found"));

        return InventoryResponse.from(inventory);
    }
}
