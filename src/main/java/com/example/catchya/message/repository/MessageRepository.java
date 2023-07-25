package com.example.catchya.message.repository;

import com.example.catchya.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesByUserId(Long userId);
}
