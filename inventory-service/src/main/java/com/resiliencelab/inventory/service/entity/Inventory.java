package com.resiliencelab.inventory.service.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "inventory")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory {
    @Id
    @Column(name = "product_id")
    private String productId;
    @Column(name = "available_quantity", nullable = false)
    private int availabelQuantity;
    @Column(name = "reserved_quantity", nullable = false)
    private int reservedQuantity;
    @Version
    private Long version;
    @Column(name = "update_at", nullable = false)
    private Instant updatedAt;

    private Inventory(String productId, int availableQuantity){
         this.productId = productId;
         this.availabelQuantity = availableQuantity;
         this.reservedQuantity = 0;
         this.updatedAt = Instant.now();
    }

    public static Inventory create(String productId, int availableQuantity){
        return new Inventory(productId, availableQuantity);

    }
     @PreUpdate
     public void updatedTimeStamp(){
         this.updatedAt = Instant.now();
    }

}
