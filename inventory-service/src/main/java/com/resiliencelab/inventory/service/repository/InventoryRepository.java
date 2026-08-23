package com.resiliencelab.inventory.service.repository;

import com.resiliencelab.inventory.service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,String> {

}
