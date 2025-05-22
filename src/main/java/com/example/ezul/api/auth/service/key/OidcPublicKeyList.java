package com.example.ezul.api.auth.service.key;

import lombok.Data;

import java.util.List;

@Data
public class OidcPublicKeyList {

    private List<OidcPublicKey> keys;

    public OidcPublicKey getMatchedKey(String kid, String alg) {
        return keys.stream()
                .filter(key -> kid.equals(key.getKid()) && alg.equals(key.getAlg()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ReturnCode.EXTERNAL_SERVER_ERROR"));
    }
}
