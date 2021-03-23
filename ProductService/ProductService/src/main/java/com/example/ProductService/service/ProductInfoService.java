package com.example.ProductService.service;

import com.example.ProductService.entity.Product;
import com.example.ProductService.entity.ProductInfo;
import com.example.ProductService.exception.ResourceNotFoundException;
import com.example.ProductService.repository.ProductInfoRepository;
import com.example.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductRepository productRepository;

    public Product addInfoProduct(String productId, ProductInfo productInfo) {
        return productRepository.findById(productId).map(product -> {
            product.setProductInfo(productInfo);
            productInfo.setProduct(product);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product updateInfoProduct(String productId, ProductInfo productInfoRequest) {
        return productRepository.findById(productId).map(product -> {
            product.getProductInfo().setProduct_sold(productInfoRequest.getProduct_sold());
            product.getProductInfo().setProduct_damaged(productInfoRequest.getProduct_damaged());
            product.getProductInfo().setProduct_returned(productInfoRequest.getProduct_returned());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
    }

    public ResponseEntity<?> deleteInfoProduct(String productId) {
        return productInfoRepository.findById(productId).map(productInfo -> {
            productInfoRepository.delete(productInfo);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
    }
}
