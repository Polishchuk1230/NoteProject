package com.example.homeworkno38.service;

import com.example.homeworkno38.model.User;
import com.example.homeworkno38.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getName())//Corrected with new Role-entity
                .disabled(!user.isEnabled())//Added for email confirmation
                .build();

        return userDetails;
    }
}
