package com.example.ezul.rest.dto;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class LoginResponse {
    String accessToken;
    String refreshToken;
    String userId;
}
