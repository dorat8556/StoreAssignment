package com.example.demo.services;


import com.example.demo.config.Status;
import com.example.demo.jpa.entities.CatalogEntity;
import com.example.demo.jpa.entities.ItemEntity;
import com.example.demo.jpa.repositories.CatalogJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogJpaRepository catalogJpaRepository;
    private final ItemService itemService;



    public CatalogEntity addNewCatalogToStore(Long storeId) {
        CatalogEntity newCatalog = new CatalogEntity(storeId);
        catalogJpaRepository.save(newCatalog);
        log.info("New Catalog Created: {}", newCatalog);
        return newCatalog;
    }

    public Status deleteCatalogById(Long catalogId) {
        catalogJpaRepository.deleteById(catalogId);
        log.info("Catalog_id {} has deleted:", catalogId);
        return Status.SUCCESS;
    }

    public void removeItemFromCatalog(Long item) {
        Optional<ItemEntity> itemEntity = itemService.getItemEntityById(item);
        if(itemEntity.isPresent()){
            ItemEntity itemToRemove = itemEntity.get();
            List<CatalogEntity> catalogs = catalogJpaRepository.findAll();
            for (CatalogEntity catalog : catalogs) {
                if (catalog.getItemsId().contains(itemToRemove)) {
                    catalog.getItemsId().remove(itemToRemove);
                    catalogJpaRepository.save(catalog);
                }
            }
        }
    }

    public void addItemToCatalog(Set<ItemEntity> itemIds, Long catalogId, Long storeId) {
        Optional<CatalogEntity> catalogEntity = catalogJpaRepository.findById(catalogId);
        if (catalogEntity.isPresent()) {
            CatalogEntity currentEntity = catalogEntity.get();
            currentEntity.addItemsToCatalog(itemIds);
            catalogJpaRepository.save(currentEntity);
        }else{
            CatalogEntity newCatalog = new CatalogEntity(storeId);
            newCatalog.addItemsToCatalog(itemIds);
            catalogJpaRepository.save(newCatalog);
        }
    }
}
