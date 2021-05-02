package com.example.demo.jpa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stores")
@Data
@NoArgsConstructor
public class StoreEntity {
    private @Id
    @GeneratedValue
    long id;

    private Long ownerId;

    public StoreEntity(Long userId) {
        this.ownerId= userId;
    }

}
