package com.example.catchya.report.dto;

import com.example.catchya.report.entity.Report;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReportInsertReq(@NotNull(message = "유저 아이디를 입력해주세요") Long userId,
                              @NotBlank(message = "신고 메세지를 입력해주세요") String content) {

    public Report toEntity() {
        return Report.builder()
                .userId(userId)
                .content(content)
                .build();
    }
}
