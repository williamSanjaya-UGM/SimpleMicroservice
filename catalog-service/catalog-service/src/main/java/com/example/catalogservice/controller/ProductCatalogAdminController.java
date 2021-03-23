package com.example.catalogservice.controller;

import com.example.catalogservice.entity.ProductCatalog;
import com.example.catalogservice.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/catalog")
public class ProductCatalogAdminController {

    @Autowired
    private ProductCatalogService productCatalogService;

    @PostMapping
    public ProductCatalog addProductToCatalog(@RequestBody ProductCatalog productCatalog) {
        return productCatalogService.addProductToCatalog(productCatalog);
    }

    @PutMapping
    public ProductCatalog updateProductInCatalog(@RequestBody ProductCatalog productCatalogRequest) {
        return productCatalogService.updateProductInCatalog(productCatalogRequest);
    }

    @DeleteMapping("/{category}/id/{productId}")
    public ResponseEntity<?> deleteProductInCatalog(@PathVariable String category,@PathVariable String productId) {
        return productCatalogService.deleteProductInCatalog(category, productId);
    }
}
