package com.tahadonuk.restaurantmanagementsystem.data.repository;

import com.tahadonuk.restaurantmanagementsystem.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Optional<Order>> findByOrderDateBetween(Date orderDate, Date orderDate2);
    boolean existsByOrderDateBetween(Date orderDate, Date orderDate2);

    List<Optional<Order>> findByOrderDate(Date orderDate);
    boolean existsByOrderDate(Date orderDate);
}
