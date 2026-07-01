package com.food_order.backend.services.implementation;

import com.food_order.backend.Repositories.CategoryRepository;
import com.food_order.backend.dto.CreateCategoryRequestDto;
import com.food_order.backend.enities.Category;
import com.food_order.backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addCategory(CreateCategoryRequestDto createCategoryRequestDto) {

        Category category =Category.builder()
                .name(createCategoryRequestDto.getName().trim())
                .description(createCategoryRequestDto.getDescription().trim())
                .build();

        categoryRepository.save(category);
    }
}
