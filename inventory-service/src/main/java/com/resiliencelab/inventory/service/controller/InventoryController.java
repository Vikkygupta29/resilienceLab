package com.resiliencelab.inventory.service.controller;


import com.resiliencelab.inventory.service.dto.InventoryResponse;
import com.resiliencelab.inventory.service.dto.ReserveInventoryRequest;
import com.resiliencelab.inventory.service.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getInventory(@PathVariable String productId){
         return ResponseEntity.ok(inventoryService.getInventoryById(productId));
    }


    @PostMapping("/{productId}/reservations")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InventoryResponse> reserveInventory(
            @PathVariable String productId,
            @Valid @RequestBody ReserveInventoryRequest request
    ) throws InterruptedException {


        return ResponseEntity.ok(
                inventoryService.reserveInventory(productId, request)
        );
    }
}
