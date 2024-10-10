package com.amananand.inventory_service.controller;

import com.amananand.inventory_service.dto.InventoryResponse;
import com.amananand.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/inventory")
@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //    localhost:8082/api/inventory/iphone_12
//    localhost:8082/api/inventory?skuCode=iphone_12&skuCode=iphone_13&skuCode=iphone_14
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
