package com.food_order.backend.Repositories;

import com.food_order.backend.dto.UserDto;
import com.food_order.backend.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);



}
