package com.example.ezul.infrastructure.persistence.external;

import com.example.ezul.infrastructure.persistence.external.dto.NaverUserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "naverAuthClient", url = "https://openapi.naver.com")
public class NaverAuthClient {

    @GetMapping("/v1/nid/me")
    public NaverUserResponse getUserProfile(@RequestHeader("Authorization") String authorizationHeader);
}
