package com.food_order.backend.services.implementation;

import com.food_order.backend.Repositories.UserRepository;
import com.food_order.backend.dto.LoginRequestDto;
import com.food_order.backend.dto.SignUpRequestDto;
import com.food_order.backend.enities.User;
import com.food_order.backend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;

    @Override
    public String singUp(SignUpRequestDto requestDto) throws RuntimeException {

        User existingUser = userRepository.findByEmail(requestDto.getEmail()).orElse( null);

        if(existingUser != null){
            throw new RuntimeException("User Already exist");
        }

        User savedUser = userRepository.save(User.builder().name(requestDto.getFullName()).email(requestDto.getEmail()).password(passwordEncoder.encode(requestDto.getPassword())).build());

        return savedUser.getEmail();

    }

    public String login(LoginRequestDto loginrequestDto) throws RuntimeException {

        User user = userRepository.findByEmail(loginrequestDto.getEmail()).orElseThrow(null);

          manager.authenticate(new UsernamePasswordAuthenticationToken(loginrequestDto.getEmail(), loginrequestDto.getPassword()));





        return "";
    }
}
