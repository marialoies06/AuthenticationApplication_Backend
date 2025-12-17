package com.example.authenticator_app.service;

import org.springframework.stereotype.Service;

import com.example.authenticator_app.dtos.ProfileRequest;
import com.example.authenticator_app.dtos.ProfileResponse;

@Service
public interface  ProfileService {
     ProfileResponse createProfile(ProfileRequest request);
   

}
