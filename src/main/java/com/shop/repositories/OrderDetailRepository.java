package com.shop.repositories;

import com.shop.entities.Order;
import com.shop.entities.OrderDetails;
import com.shop.entities.RelationshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Integer>, CrudRepository<OrderDetails, Integer> {


    List<OrderDetails> findByOrders(Order orders);
}
