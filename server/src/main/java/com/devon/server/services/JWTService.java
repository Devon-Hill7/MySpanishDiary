package com.devon.server.services;

import java.util.Map;
import java.util.HashMap;

import com.devon.server.controllers.AuthenticationController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;

import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.function.Function;
import io.jsonwebtoken.Claims;


@Service
public class JWTService {


    private String secretKey = "";

    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException("Error initializing JWTService: " + e.getMessage());
        }
    }
    
    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();
        
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .and()
                .signWith(getKey())
                .compact();

        

    }

    private SecretKey getKey() {
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
