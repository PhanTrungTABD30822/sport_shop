package com.shop.repositories;

import com.shop.entities.Customer;
import com.shop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, CrudRepository<Order, Integer> {
    List<Order> findAllByStatus(int i);

    @Query("SELECT s.createdAt, SUM(s.totalPrice) FROM Order s WHERE s.createdAt >= ?1 AND s.createdAt <= ?2 GROUP BY s.createdAt")
    List<Object[]> calculateTotalPriceByDateRange(Date startDate, Date endDate);
//    @Query("SELECT CONCAT(FUNCTION('DAY', o.time), '-', FUNCTION('MONTH', o.time)), SUM(o.totalPrice) " +
//            "FROM Order o " +
//            "WHERE o.status = 1 " +
//            "GROUP BY CONCAT(FUNCTION('DAY', o.time), '-', FUNCTION('MONTH', o.time))")
//    List<Object[]> calculateTotalPriceByStatus();
}
