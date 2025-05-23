package com.example.ezul.api.auth.service.provider;

import com.example.ezul.api.auth.service.provider.param.OidcProviderParams;

public interface OidcProvider {
    String getProviderId(OidcProviderParams params);
}
