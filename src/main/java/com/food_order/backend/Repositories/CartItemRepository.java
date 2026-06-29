package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Cartitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<Cartitem , Long> {
}
