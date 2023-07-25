package com.example.catchya.report.dto;

import jakarta.validation.constraints.NotNull;

public record ReportFindReq(@NotNull(message = "유저 아이디를 입력해주세요") Long userId) {
}
