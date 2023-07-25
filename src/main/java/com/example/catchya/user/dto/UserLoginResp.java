package com.example.catchya.user.dto;

import com.example.catchya.user.entity.User;

public record UserLoginResp(Long id, String username) {
    public UserLoginResp(User entity) {
        this(entity.getId(), entity.getUsername());
    }
}
