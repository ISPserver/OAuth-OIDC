package com.example.ezul.api.auth.controller;

import com.example.ezul.api.auth.dto.LoginRequest;
import com.example.ezul.api.auth.dto.LoginResponse;
import com.example.ezul.api.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    @PostMapping("/social/login")
    public ResponseEntity<LoginResponse> socialLogin(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.socialLogin(request);
        return ResponseEntity.ok(response);
    }
}