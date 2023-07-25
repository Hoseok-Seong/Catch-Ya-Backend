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
        if(!Objects.equals(messageInsertReq.userId(), myUserDetails.getUser().getId())) {
            return ResponseEntity.badRequest().body("유저 아이디가 달라서 권한 실패");
        }

        Message message = messageRepository.save(messageInsertReq.toEntity());

        return ResponseEntity.ok().body(new MessageInsertResp(message));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getMessages(MyUserDetails myUserDetails,
                                         MessageFindReq messageFindReq) {
        if(!Objects.equals(messageFindReq.userId(), myUserDetails.getUser().getId())) {
            return ResponseEntity.badRequest().body("유저 아이디가 달라서 권한 실패");
        }

        List<Message> messages = messageRepository.findMessagesByUserId(messageFindReq.userId());

        return ResponseEntity.ok().body(new MessageFindResp(messages));
    }
}
