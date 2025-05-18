package com.example.ezul.domain.model;

public interface OidcProvider {
    String getProviderId(String idToken);
}
