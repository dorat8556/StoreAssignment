package com.example.demo.jpa.repositories;

import com.example.demo.jpa.entities.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerJpaRepository extends JpaRepository<SellerEntity, Long>
{
}
