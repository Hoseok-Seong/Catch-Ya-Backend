package com.example.catchya.sms.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.catchya.sms.dto.SmsInsertReq;
import com.example.catchya.sms.dto.SmsInsertResp;
import com.example.catchya.sms.util.SendSms;
import com.example.catchya.sms.util.SignatureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@RequiredArgsConstructor
public class SmsService {

    private static final String serviceId = System.getenv("NCLOUD_SERVICE_ID");
    private static final String accessKey = System.getenv("NCLOUD_ACCESS_KEY");
    private static final String secretKey = System.getenv("NCLOUD_SECRET_KEY");

    private final SendSms sendSms;

    @Transactional
    public SmsInsertResp sendSMS(String to, String content, String reserveTime) throws JsonProcessingException, InvalidKeyException,
            IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException, ExecutionException, InterruptedException {

        long elapsedTimeMillis = Instant.now().toEpochMilli();
        String timestamp = String.valueOf(elapsedTimeMillis);
        String apiUrl = "https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages";
        String signature = SignatureUtil.makeSignature(timestamp, serviceId, accessKey, secretKey);

        List<SmsInsertReq.MessageDto> messages = new ArrayList<>();
        messages.add(new SmsInsertReq.MessageDto(to, content));

        LocalDateTime currentTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime targetTime = LocalDateTime.parse(reserveTime, formatter);

        long timeDifferenceMillis  = ChronoUnit.MILLIS.between(currentTime, targetTime);

        if (Math.abs(timeDifferenceMillis ) <= 10 * 60 * 1000) {
            throw new IllegalArgumentException("예약시간은 현재시간으로부터 10분 이후여야 합니다");
        } else {
            return sendSms.sendSmsMoreThan10Minutes(messages, reserveTime, timestamp, signature, apiUrl);
        }
    }

    @Transactional
    public ResponseEntity<?> cancelSMS(String requestId) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        long elapsedTimeMillis = Instant.now().toEpochMilli();
        String timestamp = String.valueOf(elapsedTimeMillis);
        String signature = SignatureUtil.makeSignatureForCancel(timestamp, serviceId, accessKey, secretKey, requestId);
        String apiUrl = "https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId +
                "/reservations/" + requestId;

        return sendSms.getSmsCancelResp(timestamp, signature, apiUrl);
    }
}
