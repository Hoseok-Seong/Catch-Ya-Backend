package com.example.catchya.message.dto;

import com.example.catchya.message.entity.Message;

public record MessageInsertResp(Long id, String username, String phone, String content) {
    public MessageInsertResp(Message entity) {
        this(entity.getId(), entity.getUsername(), entity.getPhone(), entity.getContent());
    }
}
