package com.example.ProductService.controller;

import com.example.ProductService.entity.Product;
import com.example.ProductService.entity.ProductInfo;
import com.example.ProductService.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    // add info product
    @PostMapping("/{productId}")
    public Product addInfoProduct(@PathVariable String productId, @RequestBody ProductInfo productInfo){
        return productInfoService.addInfoProduct(productId,productInfo);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productInfoService.getAllProducts();
    }

    @PutMapping("/{productId}")
    public Product updateInfoProduct(@PathVariable String productId, @RequestBody ProductInfo productInfoRequest){
        return productInfoService.updateInfoProduct(productId,productInfoRequest);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteInfoProduct(@PathVariable String productId) {
        return productInfoService.deleteInfoProduct(productId);
    }
}
