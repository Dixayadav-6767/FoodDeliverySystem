package com.food_order.backend.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String Secret = "yhdvwdvsudsecvcbiefvbeuvgfvucbdduodbldjqwudgwldbwdgqwqdbvdwdsvsvcusvuvuxscgscsy";
    private final String Exp = "20 * 60 *100";

    public String GenarateToken(String email){

        String Token = Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Exp))
                .signWith(Keys.hmacShaKeyFor(Secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
        return Token;
    }




}
