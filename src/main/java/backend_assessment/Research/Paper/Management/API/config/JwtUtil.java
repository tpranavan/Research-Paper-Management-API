package backend_assessment.Research.Paper.Management.API.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // Use a strong 32+ char secret for HS256
    private static final String SECRET_KEY = "my-very-strong-secret-key-1234567890";

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generate JWT token valid for 2 days
     */
    public static String generateToken(String username) {
        long expirationMillis = 1000 * 60 * 60 * 24 * 2; // 2 days
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validate token and return username if valid, otherwise null
     */
    public static String validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
        } catch (JwtException e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
        return null;
    }
}
