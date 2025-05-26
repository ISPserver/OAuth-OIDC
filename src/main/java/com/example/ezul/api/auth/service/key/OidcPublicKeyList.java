package com.example.ezul.api.auth.service.key;

import com.example.ezul.base.enums.ErrorCode;
import com.example.ezul.core.exception.ApiException;
import lombok.Data;

import java.util.List;

@Data
public class OidcPublicKeyList {

    private List<OidcPublicKey> keys;

    public OidcPublicKey getMatchedKey(String kid, String alg) {
        return keys.stream()
                .filter(key -> kid.equals(key.getKid()) && alg.equals(key.getAlg()))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorCode.OIDC_PUBLIC_KEY_NOT_MATCHED.getCode()));
    }
}
