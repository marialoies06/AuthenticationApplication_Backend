package com.example.authenticator_app.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authenticator_app.model.UserEntity;
import com.example.authenticator_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// Service to load user-specific data
@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    // Load user by username (email in this case)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new User(
                existingUser.getEmail(),
                existingUser.getPassword(),
                new ArrayList<>()
        );
    }
}
