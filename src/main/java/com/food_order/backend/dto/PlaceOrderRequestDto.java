package com.food_order.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceOrderRequestDto {
    private Long cartId;
    private Long addressId;
    private AddressDto addressDto;
}

