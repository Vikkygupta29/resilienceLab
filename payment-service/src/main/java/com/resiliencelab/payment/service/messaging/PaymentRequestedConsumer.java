package com.resiliencelab.payment.service.messaging;

import com.resiliencelab.payment.service.dto.PaymentRequest;
import com.resiliencelab.payment.service.dto.PaymentResponse;
import com.resiliencelab.payment.service.dto.event.PaymentCompletedEvent;
import com.resiliencelab.payment.service.dto.event.PaymentFailedEvent;
import com.resiliencelab.payment.service.dto.event.PaymentRequestedEvent;
import com.resiliencelab.payment.service.service.PaymentService;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestedConsumer {

    private final PaymentService paymentService;
    private final PaymentEventProducer paymentEventProducer;
//    private final AtomicInteger attemptCounter = new AtomicInteger(0);

    public PaymentRequestedConsumer(
            PaymentService paymentService,
            PaymentEventProducer paymentEventProducer) {

        this.paymentService = paymentService;
        this.paymentEventProducer = paymentEventProducer;
    }
    @RetryableTopic(
            attempts = "3",
            backOff = @BackOff(delay = 2000)
    )
    @KafkaListener(
            topics = "payment.requested",
            groupId = "payment-service-group"
    )
    public void consumePaymentRequested(
            PaymentRequestedEvent event) {


//        if (attempt < 3) {
//            throw new RuntimeException("Simulated temporary payment failure");
//        }
//        if (true) {
//            throw new RuntimeException("Simulated temporary payment failure");
//        }

        System.out.println("=================================");
        System.out.println("Payment Service received payment.requested");
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Amount: " + event.getAmount());

        PaymentRequest request = new PaymentRequest(
                event.getOrderId(),
                event.getAmount()
        );

        PaymentResponse response =
                paymentService.processPayment(request);

        System.out.println("Payment processed successfully!");
        System.out.println("Payment Response: " + response);

        PaymentCompletedEvent completedEvent =
                new PaymentCompletedEvent(
                        event.getOrderId(),
                        event.getAmount()
                );

        paymentEventProducer.publishPaymentCompleted(completedEvent);

        System.out.println("=================================");
    }


    @DltHandler
    public void handleDlt(PaymentRequestedEvent event) {

        System.out.println("=================================");
        System.out.println("Payment request sent to DEAD LETTER TOPIC");
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Amount: " + event.getAmount());

        PaymentFailedEvent failedEvent =
                new PaymentFailedEvent(
                        event.getOrderId().toString(),
                        event.getAmount(),
                        "Payment processing failed after all retry attempts"
                );

        paymentEventProducer.publishPaymentFailed(failedEvent);

        System.out.println("=================================");
    }
}