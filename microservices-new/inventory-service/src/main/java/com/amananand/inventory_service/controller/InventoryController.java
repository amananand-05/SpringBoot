package com.amananand.inventory_service.controller;

import com.amananand.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/inventory")
@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("skuCode") String skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
