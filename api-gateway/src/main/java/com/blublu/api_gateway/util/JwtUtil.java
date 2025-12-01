package com.blublu.api_gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
  @Value("${jwt.secret}")
  private String secret;

  private final Key SIGNING_KEY = Keys.hmacShaKeyFor(secret.getBytes());

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public List<String> extractRoles(String token) {
    Claims claims = extractAllClaims(token);
    return claims.get("username", List.class);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  // Parses and verifies signature → returns all claims
  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token).getBody();
  }

  public Boolean isTokenValid(String token, String usernameRequest) {
    final String username = extractUsername(token);
    return (username.equals(usernameRequest) && !isTokenExpired(token));
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();

    // You can add custom claims such as roles:
    claims.put("roles", userDetails.getAuthorities());

    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) {
    long now = System.currentTimeMillis();
    long TOKEN_VALIDITY = 60 * 60 * 1000;

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(now))
        .setExpiration(new Date(now + TOKEN_VALIDITY))
        .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
        .compact();
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
}