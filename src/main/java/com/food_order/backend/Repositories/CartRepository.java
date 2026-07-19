package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Cart;
import com.food_order.backend.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , Long> {
    Optional<Cart> getByUser(User user);
    Cart getById (Long id);

}
