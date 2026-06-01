package com.example.RunningApp.Security;

import com.example.RunningApp.User.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    System.out.println("=================================");
    System.out.println("LOGIN ATTEMPT");
    System.out.println("Input email: " + email);

    var user = userRepository.findByEmail(email)
            .orElseThrow(() -> {
                System.out.println("USER NOT FOUND");
                return new UsernameNotFoundException("User not found");
            });

    System.out.println("USER FOUND");
    System.out.println("DB email: " + user.getEmail());
    System.out.println("DB password: " + user.getPassword());
    System.out.println("Password length: " + user.getPassword().length());

    System.out.println("BUILDING USERDETAILS");
    System.out.println("=================================");

    return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPassword())
            .roles("USER")
            .build();
}
}