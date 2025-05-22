package com.example.ezul.api.auth.service.provider.param;

public record NaverProviderParams(String authCode, String state) implements OAuthProviderParams {}
