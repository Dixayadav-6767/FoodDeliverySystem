package com.food_order.backend.enities;

import jakarta.persistence.*;


@Entity
@Table(name = "Cart_Table")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long CartId;
    Long User_id;

    public Cart(Long cartId, Long user_id) {
        CartId = cartId;
        User_id = user_id;
    }

    public Long getCartId() {
        return CartId;
    }

    public void setCartId(Long cartId) {
        CartId = cartId;
    }

    public Long getUser_id() {
        return User_id;
    }

    public void setUser_id(Long user_id) {
        User_id = user_id;
    }
}
