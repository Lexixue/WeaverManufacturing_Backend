package com.csi.weavermanufacturing_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.csi.weavermanufacturing_backend.dtos.LoginRequest;
import com.csi.weavermanufacturing_backend.dtos.LoginResponse;
import com.csi.weavermanufacturing_backend.entity.User;
import com.csi.weavermanufacturing_backend.repository.UserRepository;
import com.csi.weavermanufacturing_backend.util.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUserName());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getUserPassword())) {
            String token = jwtUtils.generateToken(user.getUserName());
            return new LoginResponse("Login successful", true, user.getIsAdmin(), token);
        } else {
            return new LoginResponse("Invalid credentials", false, null, null);
        }
    }
}


