package com.example.homeworkno38.controller;

import com.example.homeworkno38.model.User;
import com.example.homeworkno38.repository.RoleRepository;
import com.example.homeworkno38.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor

@Controller
public class SecurityController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

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
    public String postRegister(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (this.userRepository.findByUsername(username) != null) {
            //Will be redirected to the /register again, but with an error message this time.
            return "redirect:/register?error=User%20with%20this%20username%20already%20exists!";
        }

        User user = new User(
                0,
                username,
                passwordEncoder.encode(password),
                roleRepository.findByName("USER")
        );
        this.userRepository.save(user);

        return "redirect:/notes";
    }
}
