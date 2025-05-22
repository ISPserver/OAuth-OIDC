package com.example.ezul.api.auth.controller;

import com.example.ezul.api.auth.service.AuthService;
import com.example.ezul.api.auth.model.domain.UserContext;
import com.example.ezul.api.auth.dto.LoginRequest;
import com.example.ezul.api.auth.dto.LoginResponse;
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

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request.getProvider(), request.getToken());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getBoard(UserContext user) {
        log.info("User: {}, Role: {}", user.userId(), user.role());
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

}