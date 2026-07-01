package com.food_order.backend.services.implementation;

import com.food_order.backend.Repositories.UserRepository;
import com.food_order.backend.enities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailSerice implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User nor Found"));

        return org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword()).build();
    }
}
