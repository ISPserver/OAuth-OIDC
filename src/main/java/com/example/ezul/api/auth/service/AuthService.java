package com.example.ezul.api.auth.service;

import com.example.ezul.api.auth.dto.LoginResponse;
import com.example.ezul.api.auth.service.provider.OAuthProvider;
import com.example.ezul.api.auth.service.provider.factory.OAuthProviderParamsFactory;
import com.example.ezul.api.auth.service.provider.param.OAuthProviderParams;
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

    Map<String, OAuthProvider> oauthProviderMap;
    UserService userService;

    public LoginResponse login(String providerName, String token) {
        String providerKey = providerName.toLowerCase();
        OAuthProvider provider = oauthProviderMap.get(providerKey);
        if (provider == null) {
            throw new RuntimeException(providerName);
//            throw new UnsupportedOAuthProviderException(providerName);
        }

        OAuthProviderParams providerParams = OAuthProviderParamsFactory.create(providerKey, token);
        String providerId = provider.getProviderId(providerParams);

        return new LoginResponse("accessToken", "refreshToken", "1");
//        User user = userService.findOrCreateUser(providerName, providerId);
//        String accessToken = jwtTokenService.createAccessToken(user);
//        String refreshToken = jwtTokenService.createRefreshToken(user);
//
//        return new LoginResponse(accessToken, refreshToken, user.getId().toString());
    }
}
