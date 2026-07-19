package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Cart;
import com.food_order.backend.enities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> getByCart(Cart cart);
}
