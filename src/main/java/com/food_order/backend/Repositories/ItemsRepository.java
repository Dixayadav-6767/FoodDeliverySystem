package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long> {
}
