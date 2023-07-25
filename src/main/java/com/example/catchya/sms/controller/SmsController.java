package com.example.catchya.sms.controller;

import com.example.catchya.global.security.MyUserDetails;
import com.example.catchya.sms.dto.SmsCancelReq;
import com.example.catchya.sms.dto.SmsInsertReq;
import com.example.catchya.sms.dto.SmsInsertResp;
import com.example.catchya.sms.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/api/sms/send")
    public ResponseEntity<SmsInsertResp> sendSMS(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                                 @RequestBody SmsInsertReq smsInsertReq) throws JsonProcessingException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException, ExecutionException, InterruptedException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, ExecutionException, JsonProcessingException {
        SmsInsertResp data = smsService.sendSMS(myUserDetails,
                smsInsertReq.getReserveTime(), smsInsertReq.getMessageId(),
                smsInsertReq.getAdditionalContent());
        return ResponseEntity.ok().body(data);
    }

    @DeleteMapping("/api/sms/cancel")
    public ResponseEntity<?> cancelSMS(@RequestBody SmsCancelReq smsCancelReq) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        return ResponseEntity.ok().body(smsService.cancelSMS(smsCancelReq.getRequestId()));
    }
}
