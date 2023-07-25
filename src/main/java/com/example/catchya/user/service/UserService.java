package com.example.catchya.user.service;

import com.example.catchya.global.jwt.MyJwtProvider;
import com.example.catchya.user.dto.UserLoginReq;
import com.example.catchya.user.dto.UserLoginResp;
import com.example.catchya.user.entity.User;
import com.example.catchya.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public ResponseEntity<?> login(UserLoginReq userLoginReq) {// 60Byte
        User user = userRepository
                .findByUsername(userLoginReq.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        if (passwordEncoder.matches(userLoginReq.getPassword(), user.getPassword())) {
            String jwt = MyJwtProvider.create(user);
            return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(new UserLoginResp(user));
        }
        throw new RuntimeException("패스워드 유효성 실패");
    }
}
