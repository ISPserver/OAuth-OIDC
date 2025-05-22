package com.example.ezul.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank
    private String provider; // "kakao","apple"

    @NotBlank // idToken(kakao, apple)
    private String token;
}
