package com.example.ezul.api.auth.service.provider;

import com.example.ezul.api.auth.client.KakaoOidcClient;
import com.example.ezul.api.auth.service.key.OidcPublicKeyList;
import com.example.ezul.api.auth.service.key.PublicKeyProvider;
import com.example.ezul.api.auth.service.provider.param.OidcProviderParams;
import com.example.ezul.core.jwt.JwtProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Map;

@Component("kakao")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KakaoOidcProvider implements OAuthProvider<OidcProviderParams> {

    KakaoOidcClient kakaoOidcClient;
    CacheManager cacheManager;
    PublicKeyProvider publicKeyProvider;
    JwtProvider jwtProvider;

    @Override
    @SuppressWarnings("ConstantConditions")
    public String getProviderId(OidcProviderParams params) {
        String idToken = params.idToken();
        Map<String, String> headers = jwtProvider.parseHeaders(idToken);
        try {
            OidcPublicKeyList keys = kakaoOidcClient.getKakaoPublicKeys();
            PublicKey publicKey = publicKeyProvider.generatePublicKey(headers, keys);
            return jwtProvider.parseClaims(idToken, publicKey).getSubject();
        } catch (RuntimeException e) {
//        } catch (SignatureException | InvalidKeyException e) {
            Cache cache = cacheManager.getCache("KakaoOICD");
            if (cache != null) cache.evict("getKakaoPublicKeys");

            OidcPublicKeyList newKeys = kakaoOidcClient.getKakaoPublicKeys();
            PublicKey newKey = publicKeyProvider.generatePublicKey(headers, newKeys);
            return jwtProvider.parseClaims(idToken, newKey).getSubject();
        }
    }
}
