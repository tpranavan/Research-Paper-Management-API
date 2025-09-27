package backend_assessment.Research.Paper.Management.API.config;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

class JwtUtilTestHelper {
    static Key getTestKey() {
        String secretKey = "my-very-strong-secret-key-1234567890";
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
