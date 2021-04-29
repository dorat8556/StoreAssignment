package com.example.demo.jpa.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class ProductEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    private float price;

    @ManyToOne(targetEntity = SellerEntity.class)
    @JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private SellerEntity seller;


    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    @Size(min = 1)
    @NotNull
    private Set<CategoryEntity> fallIntoCategories;


    public ProductEntity()
    {
    }

    public ProductEntity(String name, float price, SellerEntity seller, HashSet<CategoryEntity> fallIntoCategories)
    {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.fallIntoCategories = fallIntoCategories;
    }

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
//    public String getDescription()
//    {
//        return description;
//    }
//
//    public void setDescription(String description)
//    {
//        this.description = description;
//    }
//
//    public float getPrice()
//    {
//        return price;
//    }
//
//    public void setPrice(float price)
//    {
//        this.price = price;
//    }
//
//    public List<String> getImages()
//    {
//        return images;
//    }
//
//    public void setImages(List<String> images)
//    {
//        this.images = images;
//    }
//
//    public SellerEntity getSeller()
//    {
//        return seller;
//    }
//
//    public void setSeller(SellerEntity seller)
//    {
//        this.seller = seller;
//    }
//
//    public Set<CategoryEntity> getFallIntoCategories()
//    {
//        return fallIntoCategories;
//    }
//
//    public void setFallIntoCategories(HashSet<CategoryEntity> fallIntoCategories)
//    {
//        this.fallIntoCategories = fallIntoCategories;
//    }
////
////    @Override
////    public String toString()
////    {
////        return "The Product: " + getName();
////    }
}

