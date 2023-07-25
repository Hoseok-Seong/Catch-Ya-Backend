package com.example.catchya.message.dto;

import com.example.catchya.message.entity.Message;
import jakarta.validation.constraints.NotBlank;

public record MessageInsertReq(@NotBlank(message = "username을 입력해주세요") String username,
                               @NotBlank(message = "연락처를 입력해주세요") String phone,
                               @NotBlank(message = "문자 내용을 입력해주세요") String content) {
    public Message toEntity() {
        return Message.builder()
                .username(username)
                .phone(phone)
                .content(content)
                .build();
    }
}
