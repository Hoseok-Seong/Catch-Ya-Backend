package com.example.catchya.message.dto;

import jakarta.validation.constraints.NotBlank;

public record MessageFindReq(@NotBlank(message = "유저 아이디를 입력해주세요") Long userId) {
}
