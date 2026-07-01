package com.food_order.backend.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String Secret = "yhdvwdvsudsecvcbiefvbeuvgfvucbdduodbldjqwudgwldbwdgqwqdbvdwdsvsvcusvuvuxscgscsy";
    private final SecretKey key =  Keys.hmacShaKeyFor(Secret.getBytes());


    public String GenerateToken(String email) {

        String Token = Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
        return Token;
    }

    public Claims ExtractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public Boolean isTokenExpire(String token){
        return ExtractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValidate(String token, String Useremail){

        final String email = ExtractAllClaims(token).getSubject();
        return(email.equals(Useremail) && ! isTokenExpire(token));

    }


}
