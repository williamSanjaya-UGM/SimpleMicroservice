package com.example.orderservice.service;

import com.example.orderservice.controller.payload.OrderPayload;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmailService emailService;

    public Order createOrder(Order order){
        Order savedOrder = orderRepository.save(order);
        logger.info("created order: "+ savedOrder);
        sendEmailUtil(savedOrder, "Purchased Order Request !");
        return savedOrder;
    }

    private void sendEmailUtil(Order savedOrder, String subject) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(savedOrder.getUsername());
        mailMessage.setSubject(subject);
        if(savedOrder.getPayment().getPayment_method().equals("transfer")) {
            if(savedOrder.getOrder_status().equals("waiting for payment")) {
                mailMessage.setText("Order had been made, please finish your transaction by transfer to order id  " + savedOrder.getId());
            }else
                mailMessage.setText("Thanks for your payment, your order is being processed right now");
        } else
            mailMessage.setText("COD order had been made, please finish your payment later after item arrived");

        emailService.sendEmail(mailMessage);
    }

    public Optional<Order> findByUsernameAndOrderId(String username, String orderId){
        return orderRepository.findByUsernameAndId(username, orderId);
    }

    public List<Order> findByUsername(String username){
        return orderRepository.findByUsernameOrderByCreatedAtDesc(username);
    }

    public Order updateOrder(String orderId, OrderPayload orderPayload){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return orderRepository.findById(orderId).map(order -> {
            order.setOrder_status(orderPayload.getOrder_status());
            order.getPayment().setPayment_status(orderPayload.getPayment_status());
            order.getPayment().setDue_date(orderPayload.getDue_date());
            order.getPayment().setCharge_id(orderPayload.getCharge_id());
            Order savedOrder = orderRepository.save(order);

            List<OrderItem> orderItems = order.getOrderItems();

            HttpEntity<List<OrderItem>> request = new HttpEntity<>(orderItems, headers);
            restTemplate.exchange("http://ProductService/products", HttpMethod.PUT,request, OrderItem[].class);
            logger.info("succeed order: "+ savedOrder);
            sendEmailUtil(savedOrder,"Payment Success notification !");
            return savedOrder;
        }).orElseThrow(()->new ResourceNotFoundException("Sorry can't found productName"));
    }

    public ResponseEntity<?> deleteOrder(String orderId) {
        return orderRepository.findById(orderId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Sorry can't found productName"));
    }
}
