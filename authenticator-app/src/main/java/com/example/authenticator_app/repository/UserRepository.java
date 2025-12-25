package com.example.authenticator_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authenticator_app.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //find a user by email
    Optional<UserEntity> findByEmail(String email) ;
    
    //if a user with the given email exists
    Boolean existsByEmail(String email);
}