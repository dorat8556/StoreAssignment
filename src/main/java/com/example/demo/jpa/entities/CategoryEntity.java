package com.example.demo.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class CategoryEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "fallIntoCategories", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProductEntity> products = new HashSet<>();

    public CategoryEntity(String name)
    {
        this.name = name;
    }
//
//    public long getId()
//    {
//        return id;
//    }
//
//    public void setId(long id)
//    {
//        this.id = id;
//    }
//
//    public String getName()
//    {
//        return name;
//    }
//
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//
//    public Set<ProductEntity> getProducts()
//    {
//        return products;
//    }
//
//    public void setProducts(HashSet<ProductEntity> products)
//    {
//        this.products = products;
//    }
//
////    @Override
////    public String toString()
////    {
////        return "The Category: " + getName();
////    }
}

