package com.example.ezul.common.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    // TODO: extract bean
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Claims parseClaims(String jwtToken, PublicKey publicKey) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (SecurityException | io.jsonwebtoken.MalformedJwtException e) {
            throw new RuntimeException("ReturnCode.UNAUTHORIZED, Invalid JWT token, e");
        } catch (Exception e) {
            throw new RuntimeException("ReturnCode.INTERNAL_SERVER_ERROR, JWT parsing failed, e");
        }
    }

    public Map<String, String> parseHeaders(String jwtToken) {
        try {
            String[] parts = jwtToken.split("\\.");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid JWT token format");
            }
            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]));
            return objectMapper.readValue(headerJson, Map.class);
        } catch (IOException | IllegalArgumentException | JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JWT headers", e);
        }
    }
}
