package com.food_order.backend.controller;


import com.food_order.backend.dto.ApiResponseDto;
import com.food_order.backend.dto.PlaceOrderRequestDto;
import com.food_order.backend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    public ResponseEntity<ApiResponseDto<Void>> placeOrder(@RequestBody PlaceOrderRequestDto placeOrderRequestDto){

    orderService.placeOrder(placeOrderRequestDto);
    ApiResponseDto<Void> response =  ApiResponseDto.<Void>builder()
            .success(true)
            .message("Order place successfully")
            .status(HttpStatus.OK.value())
            .data(null)
            .build();
    return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
