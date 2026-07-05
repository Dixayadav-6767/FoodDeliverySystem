package com.food_order.backend.controller;

import com.food_order.backend.dto.AddItemRequestDto;
import com.food_order.backend.dto.ApiResponseDto;
import com.food_order.backend.dto.CreateCategoryRequestDto;
import com.food_order.backend.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping(value = "/add-item" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponseDto<Void>> addItem (@RequestPart("items") AddItemRequestDto addItemRequestDto ,@RequestPart("image") MultipartFile image) {

        itemService.addItem(addItemRequestDto , image);

        ApiResponseDto<Void> response =  ApiResponseDto.<Void>builder()
                .success(true)
                .message("Item Added Successfully")
                .status(HttpStatus.CREATED.value())
                .data(null).build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
