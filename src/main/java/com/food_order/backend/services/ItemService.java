package com.food_order.backend.services;

import com.food_order.backend.dto.AddItemRequestDto;

public interface ItemService {
    void addItem (AddItemRequestDto addItemRequestDto);
}
