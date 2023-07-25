package com.example.catchya.sms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SmsInsertReq {
    private String additionalContent;
    @NotBlank(message = "예약시간을 입력해주세요")
    private String reserveTime;
    @NotBlank(message = "messageId를 입력해주세요")
    private Long messageId;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MessageDto {
        private String to;
        private String content;
    }
}
