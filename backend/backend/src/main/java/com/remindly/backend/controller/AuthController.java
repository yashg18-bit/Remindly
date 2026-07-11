package com.remindly.backend.controller;

import com.remindly.backend.dto.LoginRequest;
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
    public String register(@Valid @RequestBody RegisterRequest request) {

        userService.register(request);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {

        userService.login(request);

        return "Login Successful";
    }

}
