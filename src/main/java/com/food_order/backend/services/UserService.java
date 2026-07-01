package com.food_order.backend.services;

import com.food_order.backend.dto.LoginRequestDto;
import com.food_order.backend.dto.LoginResponseDto;
import com.food_order.backend.dto.SignUpRequestDto;

public interface UserService {

   String singUp(SignUpRequestDto requestDto);
   LoginResponseDto login(LoginRequestDto requestDto);

}
