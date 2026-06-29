package com.food_order.backend.Repositories;

import com.food_order.backend.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
