package com.shop.repositories;

import com.shop.entities.Customer;
import com.shop.entities.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, CrudRepository<Order, Integer> {
    @Query("SELECT o FROM Order o join o.customer u WHERE u.email = :email")
    List<Order> findByEmail(@Param("email") String email);

}
