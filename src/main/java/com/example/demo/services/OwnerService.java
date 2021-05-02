package com.example.demo.services;

import com.example.demo.config.Status;
import com.example.demo.jpa.entities.CatalogEntity;
import com.example.demo.jpa.entities.ItemEntity;
import com.example.demo.jpa.entities.StoreEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {

    private final ValidateOwnerReqService validateOwnerReqService;
    private final StoreService storeService;
    private final CatalogService catalogService;
    private final ItemService itemService;


    public StoreEntity createNewStore(Long userId) {
        if (validateOwnerReqService.isAllowed(userId)) {
            return storeService.createNewStore(userId);
        }
        return null;
    }

    public CatalogEntity addNewCatalogToStore(Long userId, Long storeId) {
        if (validateOwnerReqService.isAllowed(userId) && isOneOfTheOwners(storeId, userId)) {
            return catalogService.addNewCatalogToStore(storeId);
        }
        return null;
    }

    public Status deleteCatalogById(Long userId, Long catalogId, Long storeId) {
        if (validateOwnerReqService.isAllowed(userId) && isOneOfTheOwners(storeId, userId)) {
            return catalogService.deleteCatalogById(catalogId);
        }
        return Status.FAILURE;
    }

    public ItemEntity addItem(Long userId, Long storeId, String itemName, Float itemPrice) {
        if (validateOwnerReqService.isAllowed(userId) && isOneOfTheOwners(storeId, userId)) {
            return itemService.addItem(itemName, itemPrice);
        }
        return null;
    }

    public Status updateItemDetails(Long userId, Long storeId, ItemEntity item) {
        if (validateOwnerReqService.isAllowed(userId) && isOneOfTheOwners(storeId, userId)) {
            return itemService.updateItemDetails(item);
        }
        return Status.FAILURE;
    }

    public Status removeItem(Long userId, Long storeId ,Long itemId) {
        if (validateOwnerReqService.isAllowed(userId) && isOneOfTheOwners(storeId,userId)) {
            catalogService.removeItemFromCatalog(itemId);
            itemService.removeItem(itemId);
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }

    public boolean isOneOfTheOwners(Long storeId, Long userId) {
        Optional<StoreEntity> storeEntity = storeService.getStoreById(storeId);
        return storeEntity.filter(entity -> entity.getOwnerId().equals(userId)).isPresent();
    }

    public Status addItemToCatalog(Long userId, Long storeId, Set<ItemEntity> itemIds, Long catalogId) {
        if (validateOwnerReqService.isAllowed(userId) && isOneOfTheOwners(storeId, userId)) {
            catalogService.addItemToCatalog(itemIds, catalogId, storeId);
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }
}
