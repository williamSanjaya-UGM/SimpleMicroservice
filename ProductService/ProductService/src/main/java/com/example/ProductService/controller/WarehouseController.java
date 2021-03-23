package com.example.ProductService.controller;

import com.example.ProductService.entity.Product;
import com.example.ProductService.entity.Warehouse;
import com.example.ProductService.service.CronJobService;
import com.example.ProductService.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@EnableBinding(Source.class)
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private CronJobService cronJobService;

    @GetMapping
    public List<Warehouse> getWarehouses(){
        return warehouseService.getWarehouses();
    }

    @GetMapping("/{id}")
    public Optional<Warehouse> getWarehouseById(@PathVariable long id){
        return warehouseService.getWarehouseById(id);
    }

    @PostMapping
    public Warehouse addWarehouse(@Valid @RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouseById(@PathVariable("id") long id){
        return warehouseService.deleteWarehouseById(id);
    }

    @PutMapping("/{id}")
    public Warehouse updateWarehouseById(@PathVariable("id") long id, @Valid @RequestBody Warehouse warehouseRequest){
        return warehouseService.updateWarehouseById(id,warehouseRequest);
    }

    // For adding product variants
    @PostMapping("/{id}/products")
    public Product addProductVariant(@PathVariable long id, @RequestBody Product product) {
        return warehouseService.addProductVariant(id,product);
    }

    // Find all products inside warehouse
    @GetMapping("/{id}/products")
    public Page<Product> getAllProductsByWarehouseId(long id, Pageable pageable){
        return warehouseService.findAllProductsByWarehouseId(id,pageable);
    }

    // Delete Specific product Variant in warehouse
    @DeleteMapping("/{warehouseId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable long warehouseId,
                                           @PathVariable String productId) {
        return warehouseService.deleteProduct(warehouseId,productId);
    }

    // Update Specific product
    @PutMapping("/{warehouseId}/products/{productId}")
    public Product updateProductById(@PathVariable long warehouseId,
                                     @PathVariable String productId,
                                     @Valid @RequestBody Product productRequest) {
        return warehouseService.updateProductById(warehouseId,productId,productRequest);
    }

    @PostMapping("/cron-job")
    public ResponseEntity<?> runCronJob() {
        cronJobService.runCronJob();
        return ResponseEntity.ok().build();
    }
}
