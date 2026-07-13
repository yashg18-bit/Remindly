package com.remindly.backend.service;

import com.remindly.backend.dto.LoginRequest;
import com.remindly.backend.dto.LoginResponse;
import com.remindly.backend.dto.RegisterRequest;
import com.remindly.backend.exception.EmailAlreadyExistsException;
import com.remindly.backend.exception.UserNotFoundException;
import com.remindly.backend.exception.dInvalidCredentialsException;
import com.remindly.backend.repository.UserRepository;
import com.remindly.backend.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.remindly.backend.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
private  final PasswordEncoder passwordEncoder;
    private  final UserRepository userRepository;
    private final JwtService jwtService;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtService jwtService){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService = jwtService;
    }
    public void register(RegisterRequest request) {
        Optional<User> existingUser =
                userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

    }
    public LoginResponse login(LoginRequest request) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            throw new UserNotFoundException("User  not found");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.get().getPassword())) {

            throw new dInvalidCredentialsException("Invalid Password");
        }

        String token = jwtService.generateToken(user.get());
        return new LoginResponse(token);
    }
}