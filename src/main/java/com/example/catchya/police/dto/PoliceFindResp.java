package com.example.catchya.police.dto;

import com.example.catchya.police.entity.Police;

import java.util.List;

public record PoliceFindResp(List<Police> policeList) {
}
