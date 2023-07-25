package com.example.catchya.report.dto;

import com.example.catchya.report.entity.Report;

public record ReportInsertResp(Long id, Long userId, String content) {
    public ReportInsertResp(Report entity) {
        this(entity.getId(), entity.getUserId(), entity.getContent());
    }
}
