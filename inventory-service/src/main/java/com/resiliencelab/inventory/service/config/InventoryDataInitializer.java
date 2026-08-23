package com.resiliencelab.inventory.service.config;


import com.resiliencelab.inventory.service.entity.Inventory;
import com.resiliencelab.inventory.service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryDataInitializer {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Bean
    CommandLineRunner seedInventory(){
          return args ->{

              if(!inventoryRepository.existsById("keyboard-1")){
               inventoryRepository.save(Inventory.create("keyboard-1",20));
              }

              if(!inventoryRepository.existsById("mouse-1")){
                  inventoryRepository.save(Inventory.create("mouse-1",80));
              }

          };
    }
}
