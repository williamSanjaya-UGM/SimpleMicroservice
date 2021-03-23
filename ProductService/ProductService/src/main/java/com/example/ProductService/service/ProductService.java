package com.example.ProductService.service;

import com.example.ProductService.dto.OrderDto;
import com.example.ProductService.entity.Product;
import com.example.ProductService.exception.ResourceNotFoundException;
import com.example.ProductService.repository.ProductInfoRepository;
import com.example.ProductService.repository.ProductRepository;
import com.example.ProductService.repository.WarehouseRepository;
import com.example.ProductService.service.payload.OrderItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ProductService {

    private static final Logger logger= LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void updateProductsById(OrderDto orderDto) {
        List<Optional<Product>> collect = orderDto.getOrderItems().stream().map(orderItem -> {
            boolean present = productInfoRepository.findById(orderItem.getProductId()).isPresent();
            if (present) {
                if(! orderDto.getPayment().getPayment_method().equals("transfer")) {
                    return productRepository.findById(orderItem.getProductId()).map(product -> {
                        int product_sold = product.getProductInfo().getProduct_sold();
                        product.setQuantity_in_units(product.getQuantity_in_units() - orderItem.getQty());
                        product.getProductInfo().setProduct_sold(product_sold + orderItem.getQty());
                        return productRepository.save(product);
                    });
                } else
                    return Optional.<Product>empty();
            } else
                throw new RuntimeException("sorry, item can't be processed");
        }).collect(Collectors.toList());
    }

    public Stream<Product> updateProductByRequest(OrderItemDto[] orderItemDtoRequest) {
        return Arrays.stream(orderItemDtoRequest).map(orderItemDto -> {
            Product productRepo = productRepository.findById(orderItemDto.getProductId()).map(product -> {
                int product_sold = product.getProductInfo().getProduct_sold();
                product.setQuantity_in_units(product.getQuantity_in_units() - orderItemDto.getQty());
                product.getProductInfo().setProduct_sold(product_sold + orderItemDto.getQty());
                return productRepository.save(product);
            }).orElseThrow(() -> new ResourceNotFoundException("productId not found"));
            return productRepo;
        });
    }

    public Optional<Product> findProductById(String id) {
        return productRepository.findById(id);
    }

}
