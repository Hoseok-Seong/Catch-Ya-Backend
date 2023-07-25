package com.example.catchya.police.dto;

import jakarta.validation.constraints.NotNull;

public record PoliceFindReq(@NotNull(message = "위도를 입력해주세요") double latitude,
                            @NotNull(message = "경도를 입력해주세요") double longitude,
                            @NotNull(message = "반경을 입력해주세요") Integer radius) {
}
