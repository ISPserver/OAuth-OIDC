package com.example.ezul.core.config;

import com.example.ezul.core.resolver.UserContextResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final UserContextResolver userContextResolver;

    public WebConfig(UserContextResolver userContextResolver) {
        this.userContextResolver = userContextResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userContextResolver);
    }
}

