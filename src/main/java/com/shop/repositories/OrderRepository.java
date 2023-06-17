package com.shop.repositories;

import com.shop.entities.Customer;
import com.shop.entities.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, CrudRepository<Order, Integer> {
    @Query("SELECT o FROM Order o JOIN o.customer cu WHERE o.nameReceive like %:keyword% OR o.phoneReceive LIKE %:keyword% OR o.addressReceive LIKE %:keyword%")
    List<Order> searchOrder(String keyword);
    @Query("SELECT COUNT(o.id) FROM Order o WHERE o.status = :status")
    Integer getCountAcceptOrder(@Param("status") Integer status);
    @Query("SELECT COUNT(o.id) FROM Order o WHERE o.status IS NULL")
    Integer getCountOrderIsNull();
    @Query("SELECT COUNT(o.id) FROM Order o WHERE o.status = 2")
    Integer getCountOrderCancel();
    @Query("select SUM(o.totalPrice) from Order o where o.status = 1")
    String getTotalPriceOrder();
    @Query("select count(o.id) from Order o ")
    Integer getTotalCustomerOrder();
}
