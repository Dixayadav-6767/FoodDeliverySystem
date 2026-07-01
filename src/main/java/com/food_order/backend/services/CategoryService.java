package com.food_order.backend.services;

import com.food_order.backend.dto.CreateCategoryRequestDto;

public interface CategoryService {
    void addCategory(CreateCategoryRequestDto createCategoryRequestDto);
}
