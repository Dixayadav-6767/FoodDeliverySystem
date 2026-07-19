package com.food_order.backend.services;

import com.food_order.backend.dto.PlaceOrderRequestDto;

public interface OrderService {
    public void placeOrder (PlaceOrderRequestDto placeOrderRequestDto);
}
