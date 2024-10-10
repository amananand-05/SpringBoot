package com.amananand.inventory_service;

//import com.amananand.inventory_service.model.Inventory;
//import com.amananand.inventory_service.repository.InventoryRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//		return args -> {
//			Inventory inventory1	= new Inventory();
//			inventory1.setSkuCode("iphone_12");
//			inventory1.setQuantity(100);
//
//			Inventory inventory2	= new Inventory();
//			inventory2.setSkuCode("iphone_11");
//			inventory2.setQuantity(100);
//
//			inventoryRepository.save(inventory1);
//			inventoryRepository.save(inventory2);
//		};
//
//	}

}
