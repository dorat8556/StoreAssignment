package com.example.demo.jpa.repositories;


import com.example.demo.jpa.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>
{
    ProductEntity findByName(String name);
}
