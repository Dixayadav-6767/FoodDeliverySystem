package com.food_order.backend.services.implementation;

import com.food_order.backend.Repositories.UserRepository;
import com.food_order.backend.dto.SignUpRequestDto;
import com.food_order.backend.enities.User;
import com.food_order.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public String singUp(SignUpRequestDto requestDto) throws RuntimeException {

        User existingUser = userRepository.findByEmail(requestDto.getEmail()).orElse( null);

        if(existingUser != null){
            throw new RuntimeException("User Already exist");
        }

        User savedUser = userRepository.save(User.builder().name(requestDto.getFullName()).email(requestDto.getEmail()).password(passwordEncoder.encode(requestDto.getPassword())).build());

        return savedUser.getEmail();

    }
}
