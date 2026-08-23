package com.resiliencelab.payment.service.service;

import com.resiliencelab.payment.service.dto.PaymentRequest;
import com.resiliencelab.payment.service.dto.PaymentResponse;
import com.resiliencelab.payment.service.entity.Payment;
import com.resiliencelab.payment.service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        Payment payment = Payment.success(request.orderId(), request.amount());

        Payment savedPayment = paymentRepository.save(payment);

        return PaymentResponse.from(savedPayment);

    }
}
