package com.example.authenticator_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileRequest {
    private String email;
    private String password;
    private String name;
}
