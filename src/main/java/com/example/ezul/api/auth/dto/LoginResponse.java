package com.example.ezul.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
public class LoginResponse {
    String accessToken;
    String refreshToken;
}
