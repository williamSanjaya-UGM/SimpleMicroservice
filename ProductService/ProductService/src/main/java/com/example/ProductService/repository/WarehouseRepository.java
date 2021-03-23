package com.example.ProductService.repository;

import com.example.ProductService.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
}
