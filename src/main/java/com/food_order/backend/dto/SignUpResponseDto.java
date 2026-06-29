package com.food_order.backend.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SignUpResponseDto {
    private String email;
}
