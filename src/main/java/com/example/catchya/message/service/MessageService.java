package com.example.catchya.message.service;

import com.example.catchya.global.security.MyUserDetails;
import com.example.catchya.message.dto.MessageInsertReq;
import com.example.catchya.message.dto.MessageInsertResp;
import com.example.catchya.message.entity.Message;
import com.example.catchya.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    @Transactional
    public ResponseEntity<?> insertMessage(MyUserDetails myUserDetails,
                                           MessageInsertReq messageInsertReq) {
        if(!Objects.equals(messageInsertReq.username(), myUserDetails.getUsername())) {
            return ResponseEntity.badRequest().body("유저 이름이 일치하지 않습니다");
        }

        Message message = messageRepository.save(messageInsertReq.toEntity());

        return ResponseEntity.ok().body(new MessageInsertResp(message));
    }
}
