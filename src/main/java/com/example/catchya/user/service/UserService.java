package com.example.catchya.user.service;

import com.example.catchya.global.jwt.MyJwtProvider;
import com.example.catchya.global.security.MyUserDetails;
import com.example.catchya.user.dto.UserJoinReq;
import com.example.catchya.user.dto.UserJoinResp;
import com.example.catchya.user.dto.UserLoginReq;
import com.example.catchya.user.dto.UserLoginResp;
import com.example.catchya.user.dto.UserUpdateReq;
import com.example.catchya.user.dto.UserUpdateResp;
import com.example.catchya.user.entity.User;
import com.example.catchya.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public ResponseEntity<?> join(UserJoinReq userJoinReq) {
        String rawPassword = userJoinReq.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword); // 60Byte
        userJoinReq.setPassword(encPassword);

        Optional<User> userOptional = userRepository.findByUsername(userJoinReq.getUsername());

        if (userOptional.isPresent()) {
            throw new DataIntegrityViolationException("사용자가 이미 존재합니다");
        }

        userRepository.save(userJoinReq.toEntity());

        // JWT 인증 로직
        User user = userRepository
                .findByUsername(userJoinReq.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));

        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            String jwt = MyJwtProvider.create(user);
            return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(new UserJoinResp(user));
        }

        throw new RuntimeException("패스워드 유효성 실패");
    }

    @Transactional
    public ResponseEntity<?> update(MyUserDetails myUserDetails, UserUpdateReq userUpdateReq) {
        User user = userRepository.findById(myUserDetails.user().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));

        user.update(userUpdateReq.password());

        return ResponseEntity.ok().body(new UserUpdateResp(user));
    }
}
