package com.amananand.inventory_service.repository;

import com.amananand.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    //    Optional<Inventory> findBySkuCode(String skuCode); // not is in use anymore
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
