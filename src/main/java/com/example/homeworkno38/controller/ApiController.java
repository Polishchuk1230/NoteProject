package com.example.homeworkno38.controller;

import com.example.homeworkno38.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("/api")
public class ApiController {
    UserRepository userRepository;

    @GetMapping("/mailcheck")
    public boolean checkEmail(@RequestParam String email) {
        return userRepository.findByEmail(email) == null;
    }
}
