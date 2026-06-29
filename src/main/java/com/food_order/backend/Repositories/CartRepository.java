package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Cart;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart , Long> {
}
