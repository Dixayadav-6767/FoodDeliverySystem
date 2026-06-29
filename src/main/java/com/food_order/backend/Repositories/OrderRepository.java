package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
