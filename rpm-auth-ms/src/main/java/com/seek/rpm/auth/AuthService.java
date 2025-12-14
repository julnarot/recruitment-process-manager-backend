package com.seek.rpm.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${security.auth.username}")
    private String staticUsername;

    @Value("${security.auth.password}")
    private String staticPassword;

    public AuthResponse login(LoginRequest request) {

        if (!staticUsername.equals(request.getUsername())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        if (!passwordEncoder.matches(request.getPassword(), staticPassword)) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(new User(staticUsername, staticPassword));

        return new AuthResponse(token);
    }

}
