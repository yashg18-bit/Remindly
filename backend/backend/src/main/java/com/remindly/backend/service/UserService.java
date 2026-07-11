package com.remindly.backend.service;

import com.remindly.backend.dto.LoginRequest;
import com.remindly.backend.dto.RegisterRequest;
import com.remindly.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.remindly.backend.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
private  final BCryptPasswordEncoder passwordEncoder;
    private  final UserRepository userRepository;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public void register(RegisterRequest request) {
        Optional<User> existingUser =
                userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

    }
    public void login(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.get().getPassword())) {

            throw new RuntimeException("Invalid Password");
        }
    }

}