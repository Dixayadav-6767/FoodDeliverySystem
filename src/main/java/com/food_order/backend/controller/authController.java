package com.food_order.backend.controller;

import com.food_order.backend.dto.*;
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

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto){

        LoginResponseDto result = userService.login(loginRequestDto);


       ApiResponseDto response =  ApiResponseDto.<LoginResponseDto>builder().success(true).message("User Login Successfully").status(HttpStatus.OK.value()).data(result).build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
