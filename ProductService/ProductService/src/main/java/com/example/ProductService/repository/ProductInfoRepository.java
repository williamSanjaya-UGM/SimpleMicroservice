package com.example.ProductService.repository;

import com.example.ProductService.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
}
