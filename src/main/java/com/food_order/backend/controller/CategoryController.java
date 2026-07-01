package com.food_order.backend.controller;

import com.food_order.backend.dto.ApiResponseDto;
import com.food_order.backend.dto.CreateCategoryRequestDto;
import com.food_order.backend.dto.LoginResponseDto;
import com.food_order.backend.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<ApiResponseDto<Void>> addCategory (@RequestBody CreateCategoryRequestDto createCategoryRequestDto) {

        categoryService.addCategory(createCategoryRequestDto);

            ApiResponseDto<Void> response =  ApiResponseDto.<Void>builder()
                    .success(true)
                    .message("Category Created").status(HttpStatus.CREATED.value())
                    .data(null).build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
