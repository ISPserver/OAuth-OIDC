package com.example.ezul.api.auth.client;

import com.example.ezul.api.auth.service.key.OidcPublicKeyList;
import com.example.ezul.core.config.OidcClientConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "KakaoOidcAuthClient",
        url = "https://kauth.kakao.com",
        configuration = OidcClientConfig.class
)
public interface KakaoOidcClient {

    @Cacheable(cacheNames = "KakaoOICD", key = "'publicKeys'", cacheManager = "oidcCacheManager")
    @GetMapping("/.well-known/jwks.json")
    OidcPublicKeyList getKakaoPublicKeys();
}

