package com.example.ezul.api.auth.dto;

import com.example.ezul.api.auth.enums.ProviderType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotNull(message = "A2001:provider")
    @Schema(description = "Social provider", allowableValues = "KAKAO, APPLE")
    ProviderType providerType;

    @NotBlank(message = "A2001:provider")
    @Schema(description = "Id Token")
    String token;
}
