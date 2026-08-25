package com.resiliencelab.order.service.controller;

import com.resiliencelab.order.service.dto.OrderRequest;
import com.resiliencelab.order.service.dto.OrderResponse;
import com.resiliencelab.order.service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request){
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(orderService.createOrder(request));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

}
