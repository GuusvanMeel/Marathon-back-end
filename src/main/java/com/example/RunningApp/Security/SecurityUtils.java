package com.example.RunningApp.Security;

import com.example.RunningApp.User.UserRepository;
import com.example.RunningApp.User.User;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    private final UserRepository userRepository;

    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // dit is het email dat je in loadUserByUsername gebruikt
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
    }

    public UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }
}