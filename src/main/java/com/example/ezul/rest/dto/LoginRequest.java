package com.example.ezul.rest.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank
    private String provider; // e.g. "kakao", "google", "apple", "naver"

    @NotBlank
    private String idToken;
}
