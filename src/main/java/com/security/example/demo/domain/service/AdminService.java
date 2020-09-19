package com.security.example.demo.domain.service;

import com.security.example.demo.domain.entity.User;
import com.security.example.demo.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }
}
