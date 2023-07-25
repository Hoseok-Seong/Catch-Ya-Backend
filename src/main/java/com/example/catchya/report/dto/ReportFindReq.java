package com.example.catchya.report.dto;

import jakarta.validation.constraints.NotBlank;

public record ReportFindReq(@NotBlank(message = "username을 입력해주세요") String username) {
}
