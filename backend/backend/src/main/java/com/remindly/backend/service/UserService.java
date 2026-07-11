package com.remindly.backend.service;

import com.remindly.backend.dto.RegisterRequest;
import com.remindly.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.remindly.backend.entity.User;

import java.time.LocalDateTime;

@Service
public class UserService {

    private  final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public void register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }
}