package com.example.ezul.api.auth.service.provider;

import com.example.ezul.api.auth.service.provider.param.OAuthProviderParams;

public interface OAuthProvider<T extends OAuthProviderParams> {
    String getProviderId(T params);
}
