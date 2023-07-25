package com.example.catchya.user.dto;

import com.example.catchya.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserJoinReq {
    @NotBlank(message = "username을 입력해주세요")
    private String username;
    @NotBlank(message = "password를 입력해주세요")
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
