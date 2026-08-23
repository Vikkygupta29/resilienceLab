package com.resiliencelab.payment.service.controller;

import com.resiliencelab.payment.service.dto.PaymentRequest;
import com.resiliencelab.payment.service.dto.PaymentResponse;
import com.resiliencelab.payment.service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED )
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request){
           return ResponseEntity.ok(paymentService.processPayment(request));
    }
}
