package com.example.homeworkno38;

import com.example.homeworkno38.repository.UserRepository;
import com.example.homeworkno38.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

import java.security.Principal;

@SpringBootTest
class HomeWorkNo38ApplicationTests {
    private UserRepository userRepository;

    @Autowired
    public HomeWorkNo38ApplicationTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void contextLoads() throws InterruptedException {
        userRepository.delete(userRepository.findById(1).get());
    }
}
