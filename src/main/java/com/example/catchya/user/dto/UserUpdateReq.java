package com.example.catchya.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record UserUpdateReq(@NotBlank(message = "password를 입력해주세요") String password) {

    @Builder
    public UserUpdateReq(String password) {
        this.password = password;
    }
}
