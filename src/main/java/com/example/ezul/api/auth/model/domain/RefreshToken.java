package com.example.ezul.api.auth.model.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken {

    String tokenValue;
    Long  userId;
    LocalDateTime expiredAt;

    public static RefreshToken create(String tokenValue, Long userId, LocalDateTime expiredAt) {
        return new RefreshToken(
                tokenValue,
                userId,
                expiredAt
        );
    }
}
