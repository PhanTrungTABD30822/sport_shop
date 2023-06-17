package com.shop.service;

import com.shop.entities.Customer;
import com.shop.entities.Order;
import com.shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrderHistoryForUser(String email) {
        return orderRepository.findByEmail(email);
    }

}
