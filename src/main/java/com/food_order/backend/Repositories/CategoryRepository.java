package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Long> {
}
