package com.example.catchya.message.controller;

import com.example.catchya.global.security.MyUserDetails;
import com.example.catchya.message.dto.MessageInsertReq;
import com.example.catchya.message.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/api/messages")
    public ResponseEntity<?> insertMessage(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                           @RequestBody @Valid MessageInsertReq messageInsertReq) {
        return messageService.insertMessage(myUserDetails, messageInsertReq);
    }
}
