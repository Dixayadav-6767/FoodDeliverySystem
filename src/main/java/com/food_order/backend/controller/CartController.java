package com.food_order.backend.controller;

import com.food_order.backend.dto.AddItemToCartRequestDto;
import com.food_order.backend.dto.ApiResponseDto;
import com.food_order.backend.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add-item")
    public ResponseEntity<ApiResponseDto<Void>> addToCart(@RequestBody AddItemToCartRequestDto addItemToCartRequestDto) {
        cartService.addToCart(addItemToCartRequestDto);

        ApiResponseDto<Void> response =  ApiResponseDto.<Void>builder()
                .success(true)
                .message("Item Added To Cart")
                .status(HttpStatus.OK.value())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
