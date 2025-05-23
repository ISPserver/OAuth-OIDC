package com.example.ezul.api.auth.service;

import com.example.ezul.api.auth.dto.LoginRequest;
import com.example.ezul.api.auth.dto.LoginResponse;
import com.example.ezul.api.auth.enums.ProviderType;
import com.example.ezul.api.auth.service.provider.OidcProvider;
import com.example.ezul.api.auth.service.provider.param.OidcProviderParams;
import com.example.ezul.api.user.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    Map<String, OidcProvider> oauthProviderMap;
    UserService userService;

    public LoginResponse socialLogin(LoginRequest request) {
        String providerName = request.getProviderType().name().toLowerCase();
        OidcProvider provider = oauthProviderMap.get(providerName);
        String providerId = provider.getProviderId(new OidcProviderParams(request.getToken()));

        boolean isExists = userService.existsUserByProvider(providerName, providerId);
        return new LoginResponse("accessToken", "refreshToken");
//        User user = userService.findOrCreateUser(providerName, providerId);
//        String accessToken = jwtTokenService.createAccessToken(user);
//        String refreshToken = jwtTokenService.createRefreshToken(user);
//
//        return new LoginResponse(accessToken, refreshToken, user.getId().toString());
    }
}
