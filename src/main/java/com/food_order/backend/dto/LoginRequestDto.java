package com.food_order.backend.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    String email;
    String password;


}
