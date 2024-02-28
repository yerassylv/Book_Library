package com.example.booklibrary.repositories;

import com.example.booklibrary.entities.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Collection<Order> findOrdersByPhone(String phone);
}
