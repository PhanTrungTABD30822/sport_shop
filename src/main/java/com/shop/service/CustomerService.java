package com.shop.service;

import com.shop.entities.Customer;
import com.shop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository userRepository;

    public void save(Customer user){
        userRepository.save(user);
    }
    public Customer findByUsername(String username) {
        return userRepository.findByName(username);
    }

}
