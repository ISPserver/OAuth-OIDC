package com.example.ezul.api.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotNull(message = "A2001:provider")
    @Schema(description = "로그인 아이디", requiredMode = REQUIRED)
    String loginId;

    @NotNull(message = "A2001:provider")
    @Schema(description = "비밀번호", requiredMode = REQUIRED)
    String password;
}
