package com.example.homeworkno38;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class HomeWorkNo38ApplicationTests {

//    @Test
//    void contextLoads() {
//    }

    @Test
    void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("ivan"));
        System.out.println(passwordEncoder.matches("ivan", "$2a$10$9VnN6yc0haOKDpS9ubE9teC3P9H9Dk8mv0Fl9mPYyjZB4NGwimQlS"));
    }
}
