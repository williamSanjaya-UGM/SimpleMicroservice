package com.example.catalogservice.service;

import com.example.catalogservice.entity.ProductCatalog;
import com.example.catalogservice.exception.ResourceNotFoundException;
import com.example.catalogservice.repository.ProductCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCatalogService {

    @Autowired
    private ProductCatalogRepository productCatalogRepository;

    public ProductCatalog addProductToCatalog(ProductCatalog productCatalog) {
        return productCatalogRepository.save(productCatalog);
    }

    public List<ProductCatalog> findAllProductInCatalog(String category) {
        return productCatalogRepository.findByCatalogKeyCategory(category);
    }

    public ProductCatalog updateProductInCatalog(ProductCatalog productCatalogRequest) {
        return productCatalogRepository.save(productCatalogRequest);
    }

    public ResponseEntity<?> deleteProductInCatalog(String category, String productId) {
        return productCatalogRepository.findByCatalogKeyCategoryAndCatalogKeyProductId(category, productId)
                .map(productCatalog -> {
                    productCatalogRepository.delete(productCatalog);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Sorry product not found"));
    }
}
