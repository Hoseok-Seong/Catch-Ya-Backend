package com.example.catchya.user.dto;

import com.example.catchya.user.entity.User;

public record UserUpdateResp(Long id, String username) {
    public UserUpdateResp(User entity) {
        this(entity.getId(), entity.getUsername());
    }
}
