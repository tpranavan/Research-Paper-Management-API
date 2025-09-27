package backend_assessment.Research.Paper.Management.API.ut;

import backend_assessment.Research.Paper.Management.API.config.JwtAuthFilter;
import backend_assessment.Research.Paper.Management.API.config.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class JwtAuthFilterTest {

    private JwtAuthFilter jwtAuthFilter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        jwtAuthFilter = new JwtAuthFilter();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);

        // reset security context before each test
        SecurityContextHolder.clearContext();
    }

    @Test
    void testDoFilterWithValidToken() throws ServletException, IOException {
        String validToken = "valid.jwt.token";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);

        // Mock JwtUtil static method
        try (MockedStatic<JwtUtil> jwtUtilMock = mockStatic(JwtUtil.class)) {
            jwtUtilMock.when(() -> JwtUtil.validateToken(validToken))
                    .thenReturn("admin");

            jwtAuthFilter.doFilterInternal(request, response, filterChain);

            assertNotNull(SecurityContextHolder.getContext().getAuthentication());
            assertEquals("admin", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            // Verify filter chain continues
            verify(filterChain, times(1)).doFilter(request, response);
        }
    }

    @Test
    void testDoFilterWithInvalidToken() throws ServletException, IOException {
        String invalidToken = "invalid.jwt.token";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + invalidToken);

        try (MockedStatic<JwtUtil> jwtUtilMock = mockStatic(JwtUtil.class)) {
            jwtUtilMock.when(() -> JwtUtil.validateToken(invalidToken))
                    .thenReturn(null); // simulate failed validation

            jwtAuthFilter.doFilterInternal(request, response, filterChain);

            assertNull(SecurityContextHolder.getContext().getAuthentication());
            verify(filterChain, times(1)).doFilter(request, response);
        }
    }

    @Test
    void testDoFilterWithoutHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }
}
