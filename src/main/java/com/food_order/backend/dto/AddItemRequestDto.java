package com.food_order.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddItemRequestDto {
    private String ItemName;
    private String ItemDescription;
    private Long price;
    private Long CategoryId;
}


