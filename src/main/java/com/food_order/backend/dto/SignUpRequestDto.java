package com.food_order.backend.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SignUpRequestDto {
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
}

