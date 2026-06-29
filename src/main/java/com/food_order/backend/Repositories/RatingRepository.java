package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Rating> {
}
