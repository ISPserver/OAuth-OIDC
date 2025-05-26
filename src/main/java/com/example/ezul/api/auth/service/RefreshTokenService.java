package com.example.ezul.api.auth.service;

import com.example.ezul.api.auth.model.domain.RefreshToken;
import com.example.ezul.api.auth.model.factory.RefreshTokenFactory;
import com.example.ezul.api.auth.repository.RefreshTokenEntityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RefreshTokenService {

    RefreshTokenEntityRepository refreshTokenEntityRepository;

    public void save(RefreshToken refreshToken) {
        refreshTokenEntityRepository.save(RefreshTokenFactory.toEntity(refreshToken));
    }
}
