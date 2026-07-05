package com.food_order.backend.services;

import com.food_order.backend.dto.AddItemRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {
    void addItem (AddItemRequestDto addItemRequestDto , MultipartFile image);
}
