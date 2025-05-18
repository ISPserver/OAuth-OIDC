package com.example.ezul.domain.model;

import com.example.ezul.infrastructure.persistence.external.NaverAuthClient;
import com.example.ezul.infrastructure.persistence.external.dto.NaverUserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component("naver")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NaverAuthProvider {

    NaverAuthClient naverAuthClient;

    public String getProviderId(String accessToken) {
        NaverUserResponse userResponse = naverAuthClient.getUserProfile("Bearer " + accessToken);
        return userResponse.getResponse().getId();
    }
}
