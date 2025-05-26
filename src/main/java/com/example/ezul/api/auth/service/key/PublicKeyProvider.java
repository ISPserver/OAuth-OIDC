package com.example.ezul.api.auth.service.key;

import com.example.ezul.base.enums.ErrorCode;
import com.example.ezul.core.exception.ApiException;
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
            throw new ApiException(ErrorCode.OIDC_PUBLIC_KEY_EMPTY.getCode());
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
            throw new ApiException(ErrorCode.OIDC_PUBLIC_KEY_BUILD_FAIL.getCode());
        }
    }
}
