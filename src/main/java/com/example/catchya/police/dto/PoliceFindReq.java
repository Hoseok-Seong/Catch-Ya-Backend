package com.example.catchya.police.dto;

import jakarta.validation.constraints.NotBlank;

public record PoliceFindReq(@NotBlank(message = "위도를 입력해주세요") double latitude,
                            @NotBlank(message = "경도를 입력해주세요") double longitude,
                            @NotBlank(message = "반경을 입력해주세요") Integer radius) {
}
