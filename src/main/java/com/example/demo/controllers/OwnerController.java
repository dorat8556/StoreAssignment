package com.example.demo.controllers;

import com.example.demo.config.Status;
import com.example.demo.jpa.entities.CatalogEntity;
import com.example.demo.jpa.entities.ItemEntity;
import com.example.demo.jpa.entities.StoreEntity;
import com.example.demo.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    // Create New Store
    @PostMapping("/create-store")
    StoreEntity createNewStore(@RequestBody Long userId) {
        return ownerService.createNewStore(userId);
    }
    //add Owner to store
    @PostMapping("/add-owner-to-store")
    Status addOwnerToStore(@RequestParam Long userId, Long storeId) {
        return ownerService.addOwnerToStore(userId,storeId);
    }

    // create/add new catalog to the store
    @PostMapping("/add-catalog")
    CatalogEntity addNewCatalogToStore(@RequestParam Long userId, Long storeId) {
        return ownerService.addNewCatalogToStore(userId, storeId);
    }

    //remove catalog from store
    @PostMapping("/delete-catalog")
    Status deleteCatalogById(@RequestParam Long userId, Long storeId, Long catalogId) {
        return ownerService.deleteCatalogById(userId, catalogId, storeId);
    }

    //add new item to catalog
    @PostMapping("/add-item")
    ItemEntity addItem(@RequestParam String itemName, Float itemPrice, Long userId, Long storeId) {
        return ownerService.addItem(userId, storeId, itemName, itemPrice);
    }

    @PostMapping("/add-item-to-catalog")
    Status addItemToCatalog(@RequestParam Long userId, Long storeId, Long catalogId
            , @RequestBody Set<ItemEntity> itemsIds) {
        return ownerService.addItemToCatalog(userId, storeId, itemsIds, catalogId);
    }

    //update item details
    @PostMapping("/update-item")
    Status updateItemDetails(@RequestParam Long userId, Long storeId, @RequestBody ItemEntity item) {
        return ownerService.updateItemDetails(userId, storeId, item);
    }

    //remove item from catalog
    @PostMapping("/remove-item")
    Status removeItem(@RequestParam Long userId, Long storeId, Long itemId) {
        return ownerService.removeItem(userId, storeId, itemId);
    }

}
