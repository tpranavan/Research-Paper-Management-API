package backend_assessment.Research.Paper.Management.API.ut;

import backend_assessment.Research.Paper.Management.API.config.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private static final String SECRET_KEY = "my-very-strong-secret-key-1234567890";

    private Key getTestKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void testGenerateAndValidateToken() {
        String username = "admin";
        String token = JwtUtil.generateToken(username);

        assertNotNull(token);

        String validatedUsername = JwtUtil.validateToken(token);
        assertEquals(username, validatedUsername);
    }

    @Test
    void testExpiredToken() throws InterruptedException {
        // Create token that expires immediately
        String token = Jwts.builder()
                .setSubject("admin")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1)) // expires in 1 ms
                .signWith(getTestKey(), SignatureAlgorithm.HS256)
                .compact();

        // Wait so it expires
        Thread.sleep(5);

        String result = JwtUtil.validateToken(token);
        assertNull(result, "Expired token should return null");
    }

    @Test
    void testInvalidToken() {
        String invalidToken = "this.is.not.a.jwt";

        String result = JwtUtil.validateToken(invalidToken);
        assertNull(result, "Invalid token should return null");
    }
}
