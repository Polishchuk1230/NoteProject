package com.example.homeworkno38.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class MailService {
    private JavaMailSender javaMailSender;

    @Async
    public void sandMail(SimpleMailMessage mail) {
        javaMailSender.send(mail);
    }

}
