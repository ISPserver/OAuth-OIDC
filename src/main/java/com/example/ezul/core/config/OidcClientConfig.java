package com.example.ezul.core.config;

import feign.FeignException;
import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class OidcClientConfig {

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100, 1000, 1);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            log.error("Feign error: methodKey={}, status={}, reason={}",
                    methodKey,
                    response.status(),
                    response.reason());

            return FeignException.errorStatus(methodKey, response);
        };
    }
}

