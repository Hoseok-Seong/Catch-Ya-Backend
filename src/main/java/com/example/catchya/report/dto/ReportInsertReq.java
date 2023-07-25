package com.example.catchya.report.dto;

import com.example.catchya.report.entity.Report;
import jakarta.validation.constraints.NotBlank;

public record ReportInsertReq(@NotBlank(message = "유저 아이디를 입력해주세요") Long userId,
                              @NotBlank(message = "신고 메세지를 입력해주세요") String content) {

    public Report toEntity() {
        return Report.builder()
                .userId(userId)
                .content(content)
                .build();
    }
}
