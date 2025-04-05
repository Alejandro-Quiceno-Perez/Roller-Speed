package com.rollerspeed.rollerspeed.helpers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.rollerspeed.rollerspeed.entity.Usuario;

import javax.crypto.SecretKey;
import io.jsonwebtoken.io.Decoders;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

       // private static final String SECRET_KEY = Base64.getEncoder().encodeToString("rollerspeed-secret-key-123456".getBytes());

       // Clave en base64 (mínimo 256 bits para HS256)
       private static final String SECRET_KEY = "GpCSjL3o5sPtTcsECtEG5rS8RlqQmYXKoJ0zTldu2x8=";

       private SecretKey getKey() {
              byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
              return Keys.hmacShaKeyFor(keyBytes);
       }

       public String getToken(Map<String, Object> claims, UserDetails userDetails) {
              return Jwts.builder()
                            .claims(claims)
                            .subject(userDetails.getUsername())
                            .issuedAt(new Date(System.currentTimeMillis()))
                            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                            .signWith(this.getKey())
                            .compact();
       }

       public String getToken(UserDetails userDetails) {
              Map<String, Object> claims = new HashMap<>();
              return getToken(claims, userDetails);
       }

       public Claims getAllClaims(String token) {
              return Jwts.parser()
                            .verifyWith(this.getKey())
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();
       }

       public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
              final Claims claims = getAllClaims(token);
              return claimsResolver.apply(claims);
       }

       public String getUserEmailFromToken(String token) {
              return getClaim(token, Claims::getSubject);
       }

       public Date getExpiration(String token) {
              return getClaim(token, Claims::getExpiration);
       }

       public boolean isTokenExpired(String token) {
              return getExpiration(token).before(new Date());
       }

       public boolean isTokenIsValid(String token, UserDetails userDetails) {
              String userEmail = getUserEmailFromToken(token);
              return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
       }
}
