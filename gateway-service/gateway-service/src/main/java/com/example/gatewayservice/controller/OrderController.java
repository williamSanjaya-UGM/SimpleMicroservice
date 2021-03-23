package com.example.gatewayservice.controller;

import com.example.gatewayservice.controller.dto.OrderDto;
import com.example.gatewayservice.controller.dto.ProductReviewDto;
import com.example.gatewayservice.service.OrderService;
import com.example.gatewayservice.service.PayloadData.PayloadOrder;
import com.example.gatewayservice.service.PayloadData.PayloadReview;
import com.example.gatewayservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@EnableBinding(Source.class)
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> doCheckout(@RequestBody PayloadOrder payloadOrder) {
        return orderService.doCheckout(payloadOrder);
    }

    @GetMapping("/order")
    public ResponseEntity<OrderDto[]> allOrderByUsername(){
        return orderService.allOrderByUsername();
    }

    @GetMapping("/order/{id}")
    public OrderDto orderById(@PathVariable String id) {
        return orderService.findOrderById(id);
    }

    // Review part
    @PostMapping("/order/review")
    public ResponseEntity<ProductReviewDto> addReview(@RequestBody PayloadReview payloadReview) {
        return reviewService.addReview(payloadReview);
    }

    @PutMapping("/order/review")
    public ResponseEntity<ProductReviewDto> updateReview(@RequestBody PayloadReview payloadReview) {
        return reviewService.addReview(payloadReview);
    }

    @DeleteMapping("/order/review/{productId}")
    public ResponseEntity<?> deleteReview(@PathVariable String productId){
        return reviewService.deleteReview(productId);
    }
}
