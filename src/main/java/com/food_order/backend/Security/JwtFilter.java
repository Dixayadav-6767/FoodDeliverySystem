package com.food_order.backend.Security;

import com.food_order.backend.services.implementation.CustomUserDetailSerice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter  extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailSerice customUserDetailSerice;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String authorizationHeader =request.getHeader("Authorization");
    String email = null;
    String jwt = null;

    try{

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            email =jwtService.ExtractAllClaims(jwt).getSubject();
        }
         if(email != null && SecurityContextHolder.getContext().getAuthentication() ==  null){

             UserDetails userDetails =customUserDetailSerice.loadUserByUsername(email);

                if(jwtService.isTokenValidate(jwt,email)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
         }

    }catch (Exception e){
        //
    }
    filterChain.doFilter(request, response);
    }
}

