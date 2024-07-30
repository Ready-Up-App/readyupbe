package com.readyup.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();

    @Value("${jwt.expiration}")
    private Long JWT_EXPIRATION;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();

    }

    public String getUsernameFromJWT(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getUsernameFromBearer(String bearer) {
        return getUsernameFromJWT(parseBearerJWT(bearer));
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JET expired or invalid");
        }
    }

    public String parseBearerJWT(String bearerToken) {
        StringBuilder ss = new StringBuilder(bearerToken);
        ss.delete(0,7);
        return ss.toString();
    }
}
