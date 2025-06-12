package com.pm.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = Base64.getDecoder().decode(       // Decode Base64 Byte[] to Byte[]  // [89, 87, 74, 106] -> [97,98,99]
                secret.getBytes(StandardCharsets.UTF_8));   // Convert Base64 String to Base64 Byte[] // "YWJj" -> [89, 87, 74, 106]  ASCII
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);      // JWT signing key, only accepts Byte[]
    }

    public String generateToken(String email, String role){
        return Jwts.builder()
                .subject(email)
                .claim("role",role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))     // 10 Hours
                .signWith(secretKey)
                .compact();
    }

}
