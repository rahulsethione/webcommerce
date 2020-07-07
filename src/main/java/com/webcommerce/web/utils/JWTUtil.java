package com.webcommerce.web.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${spring.security.jwt.secret:secret}")
    private String secret;

    @Value("${spring.security.jwt.issuer:issuer}")
    private String issuer;

    @Value("#{${spring.security.jwt.expiry}}")
    private long expiry;

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        String token = Jwts.builder()
                .setClaims(claims)
                .setId(userDetails.getUsername())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setIssuer(issuer)
                .setExpiration(new Date(new Date().getTime() + expiry * 1000))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

        return token;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(getEmail(token))
                && getClaims(token).getExpiration().after(new Date());
    }

    public String getEmail(String token) {
        return getClaims(token).getId();
    }

    private Claims getClaims(String token) throws JwtException {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token).getBody();
    }
}
