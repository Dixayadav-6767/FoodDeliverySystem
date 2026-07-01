package com.food_order.backend.controller;

import com.food_order.backend.dto.AddItemRequestDto;
import com.food_order.backend.dto.ApiResponseDto;
import com.food_order.backend.dto.CreateCategoryRequestDto;
import com.food_order.backend.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/add-item")
    public ResponseEntity<ApiResponseDto<Void>> addItem (@RequestBody AddItemRequestDto addItemRequestDto) {

        itemService.addItem(addItemRequestDto);

        ApiResponseDto<Void> response =  ApiResponseDto.<Void>builder()
                .success(true)
                .message("Item Added Successfully")
                .status(HttpStatus.CREATED.value())
                .data(null).build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
