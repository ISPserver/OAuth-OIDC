package com.example.ezul.domain.model;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

@Component
public class PublicKeyProvider {

    public PublicKey generatePublicKey(Map<String, String> tokenHeaders, OidcPublicKeyList publicKeys) {
        String kid = tokenHeaders.get("kid");
        String alg = tokenHeaders.get("alg");

        if (kid == null || alg == null) {
            throw new RuntimeException("ReturnCode.INTERNAL_SERVER_ERROR");
        }

        OidcPublicKey matchedKey = publicKeys.getMatchedKey(kid, alg);
        return buildPublicKey(matchedKey);
    }

    private PublicKey buildPublicKey(OidcPublicKey key) {
        try {
            byte[] nBytes = Base64.getUrlDecoder().decode(key.getN());
            byte[] eBytes = Base64.getUrlDecoder().decode(key.getE());

            RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(1, nBytes), new BigInteger(1, eBytes));
            return KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("ReturnCode.EXTERNAL_SERVER_ERROR");
        }
    }
}
