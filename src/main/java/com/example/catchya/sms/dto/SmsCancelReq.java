package com.example.catchya.sms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SmsCancelReq {
    @NotBlank(message = "requestId를 입력해주세요")
    private String requestId;
}
