package com.food_order.backend.controller;

import com.food_order.backend.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/auth")
public class authController {

    @PostMapping("/sign-up")
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto){

    }

}
