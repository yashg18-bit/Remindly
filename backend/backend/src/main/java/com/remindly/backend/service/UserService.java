package com.remindly.backend.service;

import com.remindly.backend.dto.RegisterRequest;
import com.remindly.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private  final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public void register(RegisterRequest request) {
        System.out.println(request);
    }
}