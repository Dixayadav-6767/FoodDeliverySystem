package com.food_order.backend.enities;

import jakarta.persistence.*;

@Entity
@Table(name = "CartItem_Table")
public class Cartitem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long CartItemId;
    Long CartId;
    Long Quantiy;
    Long ItemId;

    public Cartitem(Long cartItemId, Long cartId, Long quantiy, Long itemId) {
        CartItemId = cartItemId;
        CartId = cartId;
        Quantiy = quantiy;
        ItemId = itemId;
    }

    public Long getCartItemId() {
        return CartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        CartItemId = cartItemId;
    }

    public Long getCartId() {
        return CartId;
    }

    public void setCartId(Long cartId) {
        CartId = cartId;
    }

    public Long getQuantiy() {
        return Quantiy;
    }

    public void setQuantiy(Long quantiy) {
        Quantiy = quantiy;
    }

    public Long getItemId() {
        return ItemId;
    }

    public void setItemId(Long itemId) {
        ItemId = itemId;
    }
}
