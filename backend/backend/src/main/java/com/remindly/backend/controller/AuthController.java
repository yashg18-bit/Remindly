package com.remindly.backend.controller;

import com.remindly.backend.dto.LoginRequest;
import com.remindly.backend.dto.LoginResponse;
import com.remindly.backend.dto.MessageResponse;
import com.remindly.backend.dto.RegisterRequest;
import com.remindly.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/register")
    public MessageResponse register(@Valid @RequestBody RegisterRequest request) {

        userService.register(request);

        return new MessageResponse("User Registered Successfully");
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
