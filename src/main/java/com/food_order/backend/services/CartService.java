package com.food_order.backend.services;

import com.food_order.backend.dto.AddItemToCartRequestDto;

public interface CartService {
    void addToCart(AddItemToCartRequestDto addItemToCartRequestDto);
}
