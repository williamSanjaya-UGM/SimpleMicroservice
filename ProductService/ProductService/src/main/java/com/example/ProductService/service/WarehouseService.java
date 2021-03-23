package com.example.ProductService.service;

import com.example.ProductService.entity.Product;
import com.example.ProductService.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import com.example.ProductService.entity.Warehouse;
import com.example.ProductService.exception.ResourceNotFoundException;
import com.example.ProductService.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseService {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    public Warehouse addWarehouse(Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getWarehouses(){
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(long id){
        return warehouseRepository.findById(id);
    }

    public Warehouse updateWarehouseById(long id, Warehouse warehouseRequest){
        return warehouseRepository.findById(id).map(warehouse -> {
            warehouse.setName(warehouseRequest.getName());
            warehouse.setCity(warehouseRequest.getCity());
            warehouse.setZipCode(warehouseRequest.getZipCode());
            return warehouseRepository.save(warehouse);
        }).orElseThrow(()->new ResourceNotFoundException("WarehouseId "+ id+" not found"));
    }

    public ResponseEntity<?> deleteWarehouseById(long id) {
        return warehouseRepository.findById(id).map(warehouse -> {
            warehouseRepository.delete(warehouse);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("WarehouseId "+ id+" not found"));
    }

    // For adding product variants
    public Product addProductVariant(long warehouseId, Product product) {
        return warehouseRepository.findById(warehouseId).map(warehouse -> {
            product.setWarehouse(warehouse);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("warehouseId " + warehouseId + " not found"));
    }

    public Page<Product> findAllProductsByWarehouseId(long warehouseId, Pageable pageable){
        return productRepository.findByWarehouseId(warehouseId,pageable);
    }

    public ResponseEntity<?> deleteProduct(long warehouseId, String productId) {
        return productRepository.findByIdAndWarehouseId(productId,warehouseId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("ProductId "+productId+" not found"));
    }

    public Product updateProductById(long warehouseId,String productId, Product productRequest) {
        return productRepository.findByIdAndWarehouseId(productId,warehouseId).map(product -> {
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setQuantity_in_units(productRequest.getQuantity_in_units());
            product.setUnit_in_price(productRequest.getUnit_in_price());
            product.setDiscount_in_rupee(productRequest.getDiscount_in_rupee());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("productId or warehouseId not found"));
    }
}
