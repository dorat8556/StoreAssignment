package com.example.demo.services;

import com.example.demo.config.Status;
import com.example.demo.jpa.entities.ItemEntity;
import com.example.demo.jpa.repositories.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemJpaRepository itemJpaRepository;

    public ItemEntity addItem(String itemName, Float itemPrice) {
        ItemEntity newItem = new ItemEntity(itemName, itemPrice);
        itemJpaRepository.save(newItem);
        log.info("New item Created: {}", newItem);
        return newItem;
    }


    public Status updateItemDetails(ItemEntity item) {
        itemJpaRepository.save(item);
        return Status.SUCCESS;
    }

    public Status removeItem(Long itemId) {

        itemJpaRepository.deleteById(itemId);
        return Status.SUCCESS;
    }

    public Optional<ItemEntity> getItemEntityById(Long itemId) {
        return itemJpaRepository.findById(itemId);
    }
}
