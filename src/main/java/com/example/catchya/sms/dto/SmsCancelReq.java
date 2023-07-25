package com.example.catchya.sms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SmsCancelReq {
    @NotNull(message = "유저 아이디를 입력해주세요")
    private Long userId;
    @NotBlank(message = "requestId를 입력해주세요")
    private String requestId;
}
