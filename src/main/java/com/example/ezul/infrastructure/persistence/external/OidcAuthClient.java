package com.example.ezul.infrastructure.persistence.external;

import com.example.ezul.domain.model.OidcPublicKeyList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OidcAuthClient {

    RestClient restClient;

    public OidcPublicKeyList getPublicKeys(String publicKeyUrl) {
        return restClient.get()
                .uri(publicKeyUrl)
                .retrieve()
                .body(OidcPublicKeyList.class);
    }
}
