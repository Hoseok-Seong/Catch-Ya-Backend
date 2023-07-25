package com.example.catchya.message.service;

import com.example.catchya.global.security.MyUserDetails;
import com.example.catchya.message.dto.MessageFindReq;
import com.example.catchya.message.dto.MessageFindResp;
import com.example.catchya.message.dto.MessageInsertReq;
import com.example.catchya.message.dto.MessageInsertResp;
import com.example.catchya.message.entity.Message;
import com.example.catchya.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    @Transactional
    public ResponseEntity<?> insertMessage(MyUserDetails myUserDetails,
                                           MessageInsertReq messageInsertReq) {
        if(messageInsertReq.username().equals(myUserDetails.getUsername())) {
            return ResponseEntity.badRequest().body("유저 이름이 일치하지 않습니다");
        }

        Message message = messageRepository.save(messageInsertReq.toEntity());

        return ResponseEntity.ok().body(new MessageInsertResp(message));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getMessages(MyUserDetails myUserDetails,
                                         MessageFindReq messageFindReq) {
        if(messageFindReq.username().equals(myUserDetails.getUsername())) {
            return ResponseEntity.badRequest().body("유저 이름이 일치하지 않습니다");
        }

        List<Message> messages = messageRepository.findByUsername(messageFindReq.username());

        return ResponseEntity.ok().body(new MessageFindResp(messages));
    }

}
