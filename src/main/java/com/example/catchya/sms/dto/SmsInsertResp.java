package com.example.catchya.sms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SmsInsertResp {
    private String requestId;
    private String requestTime;
    private String statusCode;
    private String statusName;
}