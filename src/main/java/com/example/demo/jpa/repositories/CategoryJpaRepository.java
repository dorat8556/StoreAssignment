package com.example.demo.jpa.repositories;

import com.example.demo.jpa.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long>
{
    List<CategoryEntity> findAllByName(String name);
}
