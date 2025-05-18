package com.example.ezul.domain.model;

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
public class GoogleOidcProvider implements OidcProvider {

    GoogleIdTokenVerifier verifier;

    @Override
    public String getProviderId(String idToken) {
        try {
            GoogleIdToken token = verifier.verify(idToken);
            if (token == null) throw new RuntimeException("Invalid ID Token");
            return token.getPayload().getSubject();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("Failed to verify Google ID Token", e);
        }
    }
}
