package com.example.catchya.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SmsInsertReq {
    private String additionalContent;
    private String reserveTime;
    private Long messageId;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MessageDto {
        private String to;
        private String content;
    }
}
