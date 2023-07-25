package com.example.catchya.user.dto;

import com.example.catchya.user.entity.User;

public record UserJoinResp(Long id, String username) {
    public UserJoinResp(User entity) {
        this(entity.getId(), entity.getUsername());
    }
}
