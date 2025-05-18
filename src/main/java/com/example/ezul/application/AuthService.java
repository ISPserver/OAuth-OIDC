package com.example.ezul.application;

import com.example.ezul.domain.model.NaverAuthProvider;
import com.example.ezul.domain.model.OidcProvider;
import com.example.ezul.rest.dto.LoginResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    Map<String, OidcProvider> oidcProviderMap;
    UserService userService;
    JwtTokenService jwtTokenService;
    NaverAuthProvider naverAuthProvider;

    public LoginResponse login(String providerName, String idToken) {
        OidcProvider provider = oidcProviderMap.get(providerName.toLowerCase());
        if (provider == null) {
            throw new RuntimeException("ReturnCode.BAD_REQUEST, Unsupported provider:  + providerName");
        }

        String providerId;
        // 토큰에서 provider 고유 ID 추출
        if (provider.equals("naver")) {
            providerId = naverAuthProvider.getProviderId(accessToken);
            // 이후 로직 동일: UserEntity 조회 및 로그인 처리
        }
         providerId = provider.getProviderId(idToken);

        // 회원 조회 또는 신규 가입 처리
        User user = userService.findOrCreateUser(providerName, providerId);

        // 액세스/리프레시 토큰 생성
        String accessToken = jwtTokenService.createAccessToken(user);
        String refreshToken = jwtTokenService.createRefreshToken(user);

        return new LoginResponse(accessToken, refreshToken, user.getId().toString());
    }
}
