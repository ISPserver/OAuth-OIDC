package com.example.ezul.api.auth.service.provider;

import com.example.ezul.api.auth.service.provider.param.OidcProviderParams;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Component("google")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoogleOidcProvider implements OAuthProvider<OidcProviderParams> {

    GoogleIdTokenVerifier verifier;

    @Override
    public String getProviderId(OidcProviderParams params) {
        try {
            GoogleIdToken token = verifier.verify(params.idToken());
            if (token == null) throw new RuntimeException("Invalid ID Token");
            return token.getPayload().getSubject();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("Failed to verify Google ID Token", e);
        }
    }
}
