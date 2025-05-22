package com.example.ezul.api.auth.client;

import com.example.ezul.api.auth.dto.NaverTokenResponse;
import com.example.ezul.core.config.OidcClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "naverAuth",
        url="https://nid.naver.com",
        configuration = {OidcClientConfig.class}
)
public interface NaverTokenClient {

    @GetMapping("/oauth2.0/token")
    NaverTokenResponse getAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("code") String code,
            @RequestParam("state") String state
    );
}
