package com.csi.weavermanufacturing_backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    // Generate a strong secret key for HS256
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SECRET_KEY)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String username) {
        return extractClaims(token).getSubject().equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
}
