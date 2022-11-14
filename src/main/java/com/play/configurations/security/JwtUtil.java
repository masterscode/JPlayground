package com.play.configurations.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;


@Component
public class JwtUtil {


    private final String secretKey = Base64.getEncoder().encodeToString("JPlaygroundSecretKey".getBytes());

    private long validityInMilliseconds = 86400000;

    @Value("${jwt.authorities.key}")
    public String AUTHORITIES_KEY;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }


    public boolean validateToken(String token, String authenticatedUserName) {
        final String username = extractUsername(token);
        return (username.equals(authenticatedUserName) && !isTokenExpired(token));
    }

    public String generateToken(String userName, String userType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("USER_TYPE", userType);
        return createToken(claims, userName);
    }

    public String generateToken(Map<String, Object> claim, String userName) {
        Map<String, Object> claims = new HashMap<>(claim);
        return createToken(claims, userName);
    }

    public String extractAttributeFromToken(String token, String key) {
        Claims claims = this.extractAllClaims(token);
        return (String) claims.get(key);
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

    public void setValidityInMilliseconds(long validityInMilliseconds) {
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public Collection<String> extractAuthoritiesClaims(String token) {
        Claims claims = this.extractAllClaims(token);
        if (claims.get(AUTHORITIES_KEY) != null) {
            return Arrays.asList(((String) claims.get(AUTHORITIES_KEY)).split(","));
        }
        return Collections.emptyList();
    }
}
