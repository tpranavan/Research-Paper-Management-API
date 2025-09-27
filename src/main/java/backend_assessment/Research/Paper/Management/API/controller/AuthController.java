package backend_assessment.Research.Paper.Management.API.controller;

import backend_assessment.Research.Paper.Management.API.dto.LoginRequest;
import backend_assessment.Research.Paper.Management.API.dto.LoginResponse;
import backend_assessment.Research.Paper.Management.API.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}