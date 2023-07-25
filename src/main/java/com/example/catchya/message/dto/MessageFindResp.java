package com.example.catchya.message.dto;

import com.example.catchya.message.entity.Message;

import java.util.List;

public record MessageFindResp(List<Message> entity) {}
