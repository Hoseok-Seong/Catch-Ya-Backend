package com.example.catchya.sms.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SmsApiDto {
    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    private List<SmsInsertReq.MessageDto> messages;
    private String reserveTime;
    private String reserveTimeZone;
}
