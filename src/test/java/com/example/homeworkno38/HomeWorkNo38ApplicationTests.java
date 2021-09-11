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
    private MailService mailService;

    @Autowired
    public HomeWorkNo38ApplicationTests(MailService mailService) {
        this.mailService = mailService;
    }

    @Test
    void contextLoads() throws InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("mycode@mail.ua");
        mail.setTo("someones@mail.ua");
        mail.setSubject("Subject of the mail");
        mail.setText("Text of th mail111");


        System.out.println("письма отправляются...");
        for (int i=0; i<1; i++) {
            mailService.sandMail(mail);
        }
        Thread.sleep(30000);
        System.out.println("письма отправлены!!!");
    }
}
