package backend_assessment.Research.Paper.Management.API.service;

import backend_assessment.Research.Paper.Management.API.config.JwtUtil;
import backend_assessment.Research.Paper.Management.API.dto.LoginRequest;
import backend_assessment.Research.Paper.Management.API.dto.LoginResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AuthService {

    private String username;
    private String password;

    public AuthService() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource("login.json").getInputStream();
        JsonNode node = mapper.readTree(inputStream).get("user");
        this.username = node.get("username").asText();
        this.password = node.get("password").asText();
    }

    public LoginResponse login(LoginRequest request) {
        if (request.getUsername().equals(username) && request.getPassword().equals(password)) {
            String token = JwtUtil.generateToken(request.getUsername());
            return new LoginResponse(token, 172800); // 2 days in seconds
        }
        throw new RuntimeException("Invalid credentials");
    }
}