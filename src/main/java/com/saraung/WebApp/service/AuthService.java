package com.saraung.WebApp.service;

import com.saraung.WebApp.dto.LoginRequest;
import com.saraung.WebApp.dto.RegisterRequest;
import com.saraung.WebApp.entity.User;
import com.saraung.WebApp.exception.BusinessException;
import com.saraung.WebApp.repository.UserRepository;
import com.saraung.WebApp.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Register a new user
     */
    public void register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("Email already registered", HttpStatus.CONFLICT);
        }


        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("USER");          // default role
        user.setEnabled(true);
        user.setEmailVerified(false);  // verification flow later

        userRepository.save(user);
    }

    /**
     * Login user and return JWT token
     */
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.isEnabled()) {
            throw new BusinessException("Account disabled", HttpStatus.FORBIDDEN);
        }

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }

        // Generate JWT
        return jwtUtil.generateToken(user.getEmail());
    }
}
