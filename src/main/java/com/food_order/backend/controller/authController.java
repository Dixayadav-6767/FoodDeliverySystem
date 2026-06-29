package com.food_order.backend.controller;

import com.food_order.backend.dto.SignUpRequestDto;
import com.food_order.backend.dto.SignUpResponseDto;
import com.food_order.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/auth")
public class authController {

    final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp (@RequestBody SignUpRequestDto signUpRequestDto){
       String result =  userService.singUp(signUpRequestDto);
        return new ResponseEntity<>((SignUpResponseDto.builder().email(result).build()) , HttpStatus.OK);
    }

}
