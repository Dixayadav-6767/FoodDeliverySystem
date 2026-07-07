package com.food_order.backend.Repositories;

import com.food_order.backend.enities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
