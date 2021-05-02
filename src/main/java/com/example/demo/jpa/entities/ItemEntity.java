package com.example.demo.jpa.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@Embeddable
public class ItemEntity {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    private Float price;

    public ItemEntity(String itemName, Float itemPrice) {
        this.name = itemName;
        this.price = itemPrice;
    }
}

