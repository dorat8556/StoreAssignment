package com.example.demo.jpa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stores")
@Data
@NoArgsConstructor
public class StoreEntity {
    private @Id
    @GeneratedValue
    long id;

    @ElementCollection
    private Set<UserEntity> ownerId = new HashSet<>();

    public StoreEntity(UserEntity userId) {
        this.ownerId.add(userId);
    }

    public void addOwnerToStoreSet(UserEntity user) {
        this.ownerId.add(user);
    }
}
