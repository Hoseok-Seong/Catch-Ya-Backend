package com.example.catchya.message.dto;

import com.example.catchya.message.entity.Message;

public record MessageInsertResp(Long id, Long userId, String phone, String content) {
    public MessageInsertResp(Message entity) {
        this(entity.getId(), entity.getUserId(), entity.getPhone(), entity.getContent());
    }
}
