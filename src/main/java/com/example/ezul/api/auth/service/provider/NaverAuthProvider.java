package com.example.ezul.api.auth.service.provider;

import com.example.ezul.api.auth.client.NaverTokenClient;
import com.example.ezul.api.auth.client.NaverUserClient;
import com.example.ezul.api.auth.dto.NaverTokenResponse;
import com.example.ezul.api.auth.dto.NaverUserResponse;
import com.example.ezul.api.auth.service.provider.param.NaverProviderParams;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("naver")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NaverAuthProvider implements OAuthProvider<NaverProviderParams> {

    NaverTokenClient naverTokenClient;
    NaverUserClient naverUserClient;

    @Value("${auth.naver.grant_type}")
    String grantType;
    @Value("${auth.naver.client_id}")
    String clientId;
    @Value("${auth.naver.client_secret}")
    String clientSecret;

    public String getProviderId(NaverProviderParams params) {
        NaverTokenResponse tokenResponse = getAccessToken(params.authCode(), params.state());
        NaverUserResponse userResponse = naverUserClient.getUserProfile(tokenResponse.getAccessToken());
        return userResponse.getResponse().getId();
    }

    private NaverTokenResponse getAccessToken(String code, String state) {
        return naverTokenClient.getAccessToken(grantType, clientId, clientSecret, code, state);
    }
}
