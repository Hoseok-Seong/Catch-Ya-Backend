package com.example.catchya.message.dto;

import jakarta.validation.constraints.NotBlank;

public record MessageFindReq(@NotBlank(message = "username을 입력해주세요") String username) {
}
