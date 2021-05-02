package com.example.demo.jpa.repositories;

import com.example.demo.jpa.entities.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogJpaRepository extends JpaRepository<CatalogEntity, Long> {

}
