package com.example.catchya.sms.util;

import com.example.catchya.sms.dto.SmsApiDto;
import com.example.catchya.sms.dto.SmsInsertReq;
import com.example.catchya.sms.dto.SmsInsertResp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SendSms {

    private static final String accessKey = System.getenv("NCLOUD_ACCESS_KEY");
    private static final String phoneNumber = System.getenv("PHONE_NUMBER");

    private final RestTemplate restTemplate;

    @Async
    public CompletableFuture<SmsInsertResp> sendSmsWithin11Minutes(long timeDifferenceMillis,
                                                                   List<SmsInsertReq.MessageDto> messages,
                                                                   String timestamp,
                                                                   String signature,
                                                                   String apiUrl) throws JsonProcessingException {
        try {
            Thread.sleep(timeDifferenceMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SmsApiDto smsReq = SmsApiDto.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phoneNumber)
                .content("캣챠 앱에서 보내는 긴급신고 문자입니다")
                .messages(messages)
                .build();

        return CompletableFuture.completedFuture(getSmsInsertResp(timestamp, signature, apiUrl, smsReq));
    }

    public SmsInsertResp sendSmsMoreThan11Minutes(List<SmsInsertReq.MessageDto> messages,
                                                  String reserveTime,
                                                  String timestamp,
                                                  String signature,
                                                  String apiUrl) throws JsonProcessingException {

        SmsApiDto smsReq = SmsApiDto.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phoneNumber)
                .content("캣챠 앱에서 보내는 긴급신고 문자입니다")
                .messages(messages)
                .reserveTime(reserveTime)
                .reserveTimeZone("Asia/Seoul")
                .build();

        return getSmsInsertResp(timestamp, signature, apiUrl, smsReq);
    }

    private SmsInsertResp getSmsInsertResp(String timestamp, String signature, String apiUrl, SmsApiDto smsReq) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        headers.set("x-ncp-apigw-timestamp", timestamp);
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", signature);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsReq);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate.postForObject(apiUrl, requestEntity, SmsInsertResp.class);
    }

    public ResponseEntity<?> getSmsCancelResp(String timestamp, String signature, String apiUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-ncp-apigw-timestamp", timestamp);
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", signature);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate.exchange(apiUrl, HttpMethod.DELETE, requestEntity, void.class);
    }
}

