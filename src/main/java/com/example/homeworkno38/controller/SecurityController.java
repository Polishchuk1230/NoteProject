package com.example.homeworkno38.controller;

import com.example.homeworkno38.model.TokenEmailConfirmation;
import com.example.homeworkno38.model.User;
import com.example.homeworkno38.repository.RoleRepository;
import com.example.homeworkno38.repository.TokenECRepository;
import com.example.homeworkno38.repository.UserRepository;
import com.example.homeworkno38.service.MailService;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@AllArgsConstructor

@Controller
public class SecurityController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private TokenECRepository tokenECRepository;
    private MailService mailService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(name = "error", required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
        if (this.userRepository.findByUsername(username) != null) {
            //Will be redirected to the /register again, but with an error message this time.
            return "redirect:/register?error=User%20with%20this%20username%20already%20exists!";
        }

        User user = new User(
                0,
                username,
                passwordEncoder.encode(password),
                email,
                roleRepository.findByName("USER")
        );
        user = this.userRepository.save(user);

        TokenEmailConfirmation token = new TokenEmailConfirmation(user);
        tokenECRepository.save(token);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("noteservice@mail.ua");
        mail.setTo(user.getEmail());
        mail.setSubject("Email address confirmation mail");
        mail.setText("Follow the <a href=\"http://localhost:8080/confirm?confirm=" + token.getValue()
                + "\">link</a>");

        mailService.sandMail(mail);

        return "redirect:/notes";
    }

    @GetMapping("/confirm")
    public String emailConfirmation(@RequestParam("confirm") String tokenValue) {

        TokenEmailConfirmation token = tokenECRepository.findByValue(tokenValue);
        if (token != null) {
            User user = token.getUser();
            user.setEnabled(true);
            userRepository.save(user);
            tokenECRepository.delete(token);
        }

        return "redirect:/notes";
    }

    @PostMapping("/changepass")
    public String changePassword(@RequestParam("password") String newPassword, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "redirect:/logout";//разлогинивать пользователя
    }

    @PostMapping("/rememberpass")
    public String rememberPass(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            String newPassword = RandomString.make();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom("notesservice@mail.ua");
            mail.setTo(user.getEmail());
            mail.setSubject("Catch a new password");
            mail.setText("Your new password: " + newPassword);

            mailService.sandMail(mail);
        }
        return "redirect:/login";
    }

}
