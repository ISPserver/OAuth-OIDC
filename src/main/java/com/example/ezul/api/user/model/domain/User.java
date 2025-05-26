package com.example.ezul.api.user.model.domain;

import com.example.ezul.base.enums.ErrorCode;
import com.example.ezul.base.model.domain.BaseAudit;
import com.example.ezul.core.exception.ApiException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User {

    Long id;
    String provider;
    String providerId;
    String loginId;
    String password;
    String nickname;
    String profileImage;
    String email;
    String ci;
    String province;
    String city;
    String district;
    boolean residentVerified;
    boolean enabled;
    boolean removed;
    LocalDateTime lastLoginAt;
    BaseAudit audit;

    public void login(String rawPassword, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(rawPassword, this.password)) throw new ApiException(ErrorCode.INVALID_CREDENTIALS.getCode());
        checkEnabled();
        this.lastLoginAt = LocalDateTime.now();
    }

    public void updateLastLoginAt() {
        checkEnabled();
        this.lastLoginAt = LocalDateTime.now();
    }

    private void checkEnabled() {
        if (!enabled) throw new ApiException(ErrorCode.USER_DISABLED.getCode());
    }
}
