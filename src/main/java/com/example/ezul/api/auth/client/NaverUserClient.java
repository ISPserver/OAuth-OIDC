package com.example.ezul.api.auth.client;

import com.example.ezul.api.auth.dto.NaverUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "naverUserClient", url = "https://openapi.naver.com")
public interface NaverUserClient {

    @GetMapping("/v1/nid/me")
    NaverUserResponse getUserProfile(@RequestHeader("Authorization") String authorizationHeader);
}
