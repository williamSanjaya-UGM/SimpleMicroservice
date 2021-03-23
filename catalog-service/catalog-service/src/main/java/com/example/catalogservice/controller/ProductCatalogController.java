package com.example.catalogservice.controller;

import com.example.catalogservice.entity.ProductCatalog;
import com.example.catalogservice.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class ProductCatalogController {

    @Autowired
    private ProductCatalogService productCatalogService;

    @GetMapping("/{category}")
    public List<ProductCatalog> findAllProductInCatalog(@PathVariable String category) {
        return productCatalogService.findAllProductInCatalog(category);
    }
}
