package com.example.authenticator_app.service;


import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.authenticator_app.dtos.ProfileRequest;
import com.example.authenticator_app.dtos.ProfileResponse;
import com.example.authenticator_app.model.UserEntity;
import com.example.authenticator_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        //convert request to user entity
        UserEntity newProfile = convertToUserEntity(request);

        //check if email already exists
        if(!userRepository.existsByEmail(request.getEmail())) {
            newProfile = userRepository.save(newProfile);
            return convertToProfileResponse(newProfile);
        }
        //throw conflict exception
        throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already in use");
    }

    //convert user entity to profile response
    private ProfileResponse convertToProfileResponse(UserEntity newProfile) {
        return ProfileResponse .builder()
                .name(newProfile.getName())
                .email(newProfile.getEmail())
                .userId(newProfile.getUserId())
                .isAccountVerified(newProfile.getIsAccountVerified())
                .build();
    }

    //convert profile request to user entity
    private UserEntity convertToUserEntity(ProfileRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .userId(UUID.randomUUID().toString())
                .name(request.getName())
                .password(request.getPassword())
                .isAccountVerified(false)
                .resetOtpExpireAt(0L)
                .verifyOtp(null)
                .verifyOtpExpireAt(0L)
                .resetOtp(null)
                .build();
    }
}
