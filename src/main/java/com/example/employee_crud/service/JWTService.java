package com.example.employee_crud.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private static final long AccessTokenExpiration =1000*60*10;
    private static final long RefreshTokenExpiration = 1000*60*60;

    private String secreatkey="";
    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk= keyGen.generateKey();
            secreatkey=Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateToken(String username,Map<String, Object> additionalClaims) {
        additionalClaims.put("token_type", "access");

        return Jwts.builder()
                .setClaims(additionalClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + AccessTokenExpiration))
                .signWith(getKey())
                .compact();

    }
    public String generateToken(String username) {
        return generateToken(username, new HashMap<>());
    }


    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("token_type", "refresh");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + RefreshTokenExpiration))
                .signWith(getKey())
                .compact();
    }




    private SecretKey getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secreatkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public long getTokenExpirationInSeconds() {
        return AccessTokenExpiration/ 1000; // Convert milliseconds to seconds
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUsernameFromRefreshToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public boolean validateRefreshToken(String token) {
        return !isTokenExpired(token);
    }
    public long getRefreshTokenExpirationInSeconds() {
        return RefreshTokenExpiration/1000;
    }


    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
