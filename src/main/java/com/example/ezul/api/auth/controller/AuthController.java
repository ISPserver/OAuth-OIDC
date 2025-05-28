package com.example.ezul.api.auth.controller;

import com.example.ezul.api.auth.dto.LoginRequest;
import com.example.ezul.api.auth.dto.SocialLoginRequest;
import com.example.ezul.api.auth.dto.LoginResponse;
import com.example.ezul.api.auth.model.domain.UserContext;
import com.example.ezul.api.auth.service.AuthService;
import com.example.ezul.base.dto.ApiResult;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/auth")

public class AuthController {

    AuthService authService;

    @PostMapping("/social/login")
    public ResponseEntity<ApiResult<LoginResponse>> socialLogin(@Valid @RequestBody SocialLoginRequest request) {
        return ResponseEntity.ok(new ApiResult<>(authService.socialLogin(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResult<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(new ApiResult<>(authService.login(request)));
    }

    @GetMapping("/test")
    public ResponseEntity<ApiResult<LoginResponse>> login(UserContext userContext) {
        log.info("UserContext: {}", userContext);
        return ResponseEntity.ok(new ApiResult<>());
    }
}