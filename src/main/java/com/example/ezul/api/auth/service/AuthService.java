package com.example.ezul.api.auth.service;

import com.example.ezul.api.auth.dto.LoginRequest;
import com.example.ezul.api.auth.dto.LoginResponse;
import com.example.ezul.api.auth.dto.SocialLoginRequest;
import com.example.ezul.api.auth.model.domain.RefreshToken;
import com.example.ezul.api.auth.service.provider.OidcProvider;
import com.example.ezul.api.auth.service.provider.param.OidcProviderParams;
import com.example.ezul.api.user.model.domain.User;
import com.example.ezul.api.user.service.UserService;
import com.example.ezul.base.enums.ErrorCode;
import com.example.ezul.core.exception.ApiException;
import com.example.ezul.core.jwt.JwtProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    Map<String, OidcProvider> oauthProviderMap;
    RefreshTokenService refreshTokenService;
    UserService userService;
    PasswordEncoder passwordEncoder;
    JwtProvider jwtProvider;

    public LoginResponse socialLogin(SocialLoginRequest request) {
        String providerName = request.getProviderType().name().toLowerCase();
        OidcProvider provider = oauthProviderMap.get(providerName);
        String providerId = provider.getProviderId(new OidcProviderParams(request.getToken()));

        User user = userService.getUserByProvider(providerName, providerId);
        user.updateLastLoginAt();
        userService.save(user);

        return generateAuthTokens(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userService.getUser(request.getLoginId());
        if (user == null) throw new ApiException(ErrorCode.INVALID_CREDENTIALS.getCode());

        user.login(request.getPassword(), passwordEncoder);
        userService.save(user);

        return generateAuthTokens(user);
    }

    private LoginResponse generateAuthTokens(User user) {
        Long userId = user.getId();
        String accessToken = jwtProvider.generateAccessToken(userId);
        String refreshToken = jwtProvider.generateRefreshToken(userId);
        LocalDateTime expiredAt = jwtProvider.getExpiration(refreshToken);

        refreshTokenService.save(RefreshToken.create(refreshToken, userId, expiredAt));

        return new LoginResponse(accessToken, refreshToken);
    }
}
