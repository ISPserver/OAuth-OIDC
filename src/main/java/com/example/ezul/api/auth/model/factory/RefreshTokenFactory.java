package com.example.ezul.api.auth.model.factory;

import com.example.ezul.api.auth.model.domain.RefreshToken;
import com.example.ezul.api.auth.model.entity.RefreshTokenEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenFactory {

    public static RefreshTokenEntity toEntity(RefreshToken refreshToken) {
        return new RefreshTokenEntity(
                refreshToken.getTokenValue(),
                refreshToken.getUserId(),
                refreshToken.getExpiredAt()
        );
    }
}
