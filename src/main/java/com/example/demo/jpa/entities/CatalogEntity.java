package com.example.demo.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "catalogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogEntity {

    private @Id
    @GeneratedValue
    long id;

    private Long storeId;

    @ElementCollection
    private Set<ItemEntity> itemsId = new HashSet<>();


    public CatalogEntity(Long storeId) {
        this.storeId = storeId;
    }

    public void addItemsToCatalog(Set<ItemEntity> items){
        this.itemsId.addAll(items);
    }
}
