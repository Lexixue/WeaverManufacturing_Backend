package com.csi.weavermanufacturing_backend.controller;

import com.csi.weavermanufacturing_backend.dtos.LoginRequest;
import com.csi.weavermanufacturing_backend.dtos.LoginResponse;
import com.csi.weavermanufacturing_backend.entity.User;
import com.csi.weavermanufacturing_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUserName());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getUserPassword())) {
            return new LoginResponse("Login successful", true, user.getIsAdmin());
        } else {
            return new LoginResponse("Invalid credentials", false, null);
        }
    }
}
