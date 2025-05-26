package com.example.ezul.api.auth.client;

import com.example.ezul.api.auth.service.key.OidcPublicKeyList;
import com.example.ezul.core.config.OidcClientConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "AppleOidcAuthClient",
        url = "https://appleid.apple.com",
        configuration = OidcClientConfig.class
)
public interface AppleOidcClient {

    @Cacheable(cacheNames = "AppleOICD", key = "'publicKeys'", cacheManager = "oidcCacheManager")
    @GetMapping("/auth/keys")
    OidcPublicKeyList getApplePublicKeys();
}
