package com.example.ProductService.repository;

import com.example.ProductService.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    Page<Product> findByWarehouseId(long warehouseId, Pageable pageable);
    Optional<Product> findByIdAndWarehouseId(String id, long warehouseId);
}
