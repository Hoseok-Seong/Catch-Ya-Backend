package com.example.catchya.message.dto;

import com.example.catchya.message.entity.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageInsertReq(@NotNull(message = "유저 아이디를 입력해주세요") Long userId,
                               @NotBlank(message = "연락처를 입력해주세요") String phone,
                               @NotBlank(message = "문자 내용을 입력해주세요") String content) {
    public Message toEntity(){
        return Message.builder()
                .userId(userId)
                .phone(phone)
                .content(content)
                .build();
    }
}
