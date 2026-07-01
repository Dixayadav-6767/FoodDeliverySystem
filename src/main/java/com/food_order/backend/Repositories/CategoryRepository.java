package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category , Long> {
    Optional<Category> findById(Long id);
}
