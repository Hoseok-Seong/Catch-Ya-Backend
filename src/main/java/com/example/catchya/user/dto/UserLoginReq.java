package com.example.catchya.user.dto;

import com.example.catchya.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginReq {
    @NotBlank(message = "username을 입력해주세요")
    private String username;
    @NotBlank(message = "password를 입력해주세요")
    private String password;

    public UserLoginReq(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
