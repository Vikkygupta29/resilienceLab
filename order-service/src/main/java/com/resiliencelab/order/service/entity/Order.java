package com.resiliencelab.order.service.entity;

import com.resiliencelab.order.service.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "created_at",nullable = false, updatable = false)
    private Instant createdAt;


   private Order(String productId, int quantity, BigDecimal amount){
       this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
        this.status = OrderStatus.CREATED;
        this.createdAt = Instant.now();

   }

  public static Order create(String productId, int quantity, BigDecimal amount){
       return new Order(productId, quantity, amount);
  }


}
