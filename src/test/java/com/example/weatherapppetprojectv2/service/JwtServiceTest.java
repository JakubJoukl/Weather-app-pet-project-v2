package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.entity.Authority;
import com.example.weatherapppetprojectv2.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

class JwtServiceTest {
    private final String SECRET = "SuperTestovaciKlicSuperTestovaciKlicSuperTestovaciKlicSuperTestovaciKlicSuperTestovaciKlicSuperTestovaciKlic";
    private final JwtService jwtService = new JwtService(SECRET);
    private String token;
    private String subjectUsername = "Test";
    private Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
    private HashMap<String, Authority> claims = new HashMap<>();

    @BeforeEach
    public void setup() {
        Authority authority = new Authority();
        authority.setAuthority("USER");
        claims.put(authority.getAuthority(), authority);
        token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subjectUsername)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate) //60 minut
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Test
    void validateToken() {
        User user = new User();
        user.setUsername(subjectUsername);
        Assertions.assertTrue(jwtService.validateToken(token, user));
    }

    @Test
    void extractUsername() {
        Assertions.assertEquals(jwtService.extractUsername(token), subjectUsername);
    }

    @Test
    void generateToken() {
        String token = jwtService.generateToken(subjectUsername);
        Assertions.assertNotNull(token);
    }

    @Test
    void extractExpiration() {
        long difference = expirationDate.getTime() - jwtService.extractExpiration(token).getTime();
        Assertions.assertTrue(difference <= 1000);
    }

    @Test
    void extractClaim() {
        Assertions.assertEquals(subjectUsername, jwtService.extractClaim(token, Claims::getSubject));
    }
}