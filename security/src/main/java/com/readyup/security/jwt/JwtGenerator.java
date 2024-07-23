package com.readyup.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {


    @Value("${jwt.expiration}")
    private Long JWT_EXPIRATION;

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    private JwtParser jwtParser;

    public JwtGenerator(){
        jwtParser = Jwts.parser().decryptWith(getSigningKey()).build();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(getSigningKey())
                .compact();

    }

    public String getUsernameFromJWT(String token) {
        Claims claims = jwtParser.parseEncryptedClaims(token).getPayload();
        return claims.getSubject();
    }
}
