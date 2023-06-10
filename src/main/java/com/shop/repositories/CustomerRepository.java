package com.shop.repositories;

import com.shop.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, CrudRepository<Customer, Integer> {
    Customer findByEmail(String email);
    Customer findByName(String me);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phoneNumber);


}
