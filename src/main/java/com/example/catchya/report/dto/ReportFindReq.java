package com.example.catchya.report.dto;

import jakarta.validation.constraints.NotBlank;

public record ReportFindReq(@NotBlank(message = "유저 아이디를 입력해주세요") Long userId) {
}
