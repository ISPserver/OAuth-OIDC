package com.example.ezul.api.auth.service.provider.factory;

import com.example.ezul.api.auth.service.provider.param.NaverProviderParams;
import com.example.ezul.api.auth.service.provider.param.OAuthProviderParams;
import com.example.ezul.api.auth.service.provider.param.OidcProviderParams;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthProviderParamsFactory {

    public static OAuthProviderParams create(String provider, String token) {
//    public static OAuthProviderParams create(String provider, Map<String, String> params) {
        return switch (provider.toLowerCase()) {
//            case "naver" -> new NaverProviderParams(params.get("code"), params.get("state"));
            case "google", "apple" -> new OidcProviderParams(token);
            default -> throw new RuntimeException(provider);
//            default -> throw new UnsupportedOAuthProviderException(provider);
        };
    }
}
