package com.example.ezul.domain.model;

import com.example.ezul.common.jwt.JwtProvider;
import com.example.ezul.infrastructure.persistence.external.OidcAuthClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Map;

@Component("kakao")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KakaoOidcProvider implements OidcProvider {

    OidcAuthClient oidcAuthClient;
    PublicKeyProvider publicKeyProvider;
    JwtProvider jwtProvider;
    @Value("${oauth.kakao.public-key-url}")
    String publicKeyUrl;

    @Override
    public String getProviderId(String idToken) {
        OidcPublicKeyList keys = oidcAuthClient.getPublicKeys(publicKeyUrl);
        Map<String, String> headers = jwtProvider.parseHeaders(idToken);
        PublicKey publicKey = publicKeyProvider.generatePublicKey(headers, keys);
        return jwtProvider.parseClaims(idToken, publicKey).getSubject();
    }
}
