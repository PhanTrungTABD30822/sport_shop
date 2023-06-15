package com.shop.repositories;

import com.shop.entities.Customer;
import com.shop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, CrudRepository<Order, Integer> {
}
