package com.food_order.backend.services.implementation;

import com.food_order.backend.Repositories.CategoryRepository;
import com.food_order.backend.Repositories.ItemsRepository;
import com.food_order.backend.dto.AddItemRequestDto;
import com.food_order.backend.enities.Category;
import com.food_order.backend.enities.Items;
import com.food_order.backend.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void addItem(AddItemRequestDto addItemRequestDto) {

        Category category = categoryRepository.findById(addItemRequestDto.getCategoryId()).orElseThrow(()-> new RuntimeException("Category Not found"));

        Items item = Items.builder()
                .name(addItemRequestDto.getItemName())
                .description(addItemRequestDto.getItemDescription())
                .category(category)
                .price(addItemRequestDto.getPrice())
                .build();
        itemsRepository.save(item);
    }
}
