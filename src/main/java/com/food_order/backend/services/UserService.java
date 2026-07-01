package com.food_order.backend.services;

import com.food_order.backend.dto.LoginRequestDto;
import com.food_order.backend.dto.SignUpRequestDto;

public interface UserService {

   String singUp(SignUpRequestDto requestDto);
   String login(LoginRequestDto requestDto);

}
