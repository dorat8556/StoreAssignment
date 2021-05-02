package com.example.demo.services;

import com.example.demo.jpa.entities.StoreEntity;
import com.example.demo.jpa.entities.UserEntity;
import com.example.demo.jpa.repositories.StoreJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {

    private final StoreJpaRepository storeJpaRepository;

    public StoreEntity createNewStore(UserEntity userId) {
        StoreEntity newStore = new StoreEntity(userId);
        storeJpaRepository.save(newStore);
        log.info("New Store Created: {}", newStore);
        return newStore;
    }

    public Optional<StoreEntity> getStoreById(Long storeId){
        return  storeJpaRepository.findById(storeId);
    }

    public void addOwnerToStore(UserEntity user, Long storeId) {
        Optional<StoreEntity> storeEntity = storeJpaRepository.findById(storeId);
        storeEntity.ifPresent(entity -> entity.addOwnerToStoreSet(user));
    }
}
