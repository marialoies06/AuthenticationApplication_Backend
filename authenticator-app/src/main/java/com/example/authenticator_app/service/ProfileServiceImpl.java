package com.example.authenticator_app.service;


import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.authenticator_app.dtos.ProfileRequest;
import com.example.authenticator_app.dtos.ProfileResponse;
import com.example.authenticator_app.model.User;
import com.example.authenticator_app.repository.UserRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository = null;



    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        User newProfile = convertToUser(request);
        newProfile = (User) userRepository.save(newProfile);
        return convertToProfileResponse(newProfile);
    }

    private ProfileResponse convertToProfileResponse(User newProfile) {
        return ProfileResponse .builder()
                .name(newProfile.getName())
                .email(newProfile.getEmail())
                .userId(newProfile.getUserId())
                .isAccountVerified(String.valueOf(newProfile.getIsAccountVerified()))
                .build();
    }

    private User convertToUser(ProfileRequest request) {
        return User.builder()
                .email(request.getEmail())
                .userId(UUID.randomUUID().toString())
                .name(request.getName())
                .password(request.getPassword())
                .isAccountVerified(false)
                .verifyOtp(null)
                .verifyOtpExpireAt(0L)
                .resetOtp(null)
                .resetOtpExpireAt(0L)
                .build();
    }
}
