package com.example.ezul.core.jwt;

import com.example.ezul.base.enums.ErrorCode;
import com.example.ezul.base.enums.ResultCode;
import com.example.ezul.core.exception.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.PublicKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtProvider {

    Key key;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 60 * 1000L;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 60 * 60 * 1000L;

    ObjectMapper objectMapper;

    public JwtProvider(@Value("${jwt.secret-key}") String secretKey, ObjectMapper objectMapper) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.objectMapper = objectMapper;
    }

    public String generateAccessToken(Long userId) {
        return generateTokenValue(userId, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String generateRefreshToken(Long userId) {
        return generateTokenValue(userId, REFRESH_TOKEN_EXPIRE_TIME);
    }

    private String generateTokenValue(Long userId, long validityInMilliseconds) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserId(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String refreshToken(String token) {
        Claims body = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        Long userId = Long.parseLong(body.getSubject());

        return generateTokenValue(userId, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public LocalDateTime getExpiration(String token) {
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return convertToLocalDateTime(expirationDate);
    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public ResultCode validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            throw new ApiException(ErrorCode.INVALID_JWT_TOKEN.getCode());
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token : " + e.getMessage());
            throw new ApiException(ErrorCode.EXPIRED_JWT_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw new ApiException(ErrorCode.UNSUPPORTED_JWT_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            throw new ApiException(ErrorCode.EMPTY_JWT_TOKEN.getCode());
        }

        return ResultCode.SUCCESS;
    }
    
    public Claims parseOidcClaims(String jwtToken, PublicKey publicKey) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (SecurityException | io.jsonwebtoken.MalformedJwtException e) {
            throw new ApiException(ErrorCode.INVALID_JWT_TOKEN.getCode());
        } catch (ExpiredJwtException e) {
            throw new ApiException(ErrorCode.EXPIRED_JWT_TOKEN.getCode());
        } catch (Exception e) {
            log.info("JWT parse error", e);
            throw new ApiException(ErrorCode.JWT_PARSE_ERROR.getCode());
        }
    }

    public Map<String, String> parseHeaders(String jwtToken) {
        try {
            String[] parts = jwtToken.split("\\.");
            if (parts.length < 2) throw new ApiException(ErrorCode.INVALID_JWT_TOKEN.getCode());
            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]));
            return objectMapper.readValue(headerJson, Map.class);
        } catch (Exception e) {
            log.info("Failed to parse JWT headers", e);
            throw new ApiException(ErrorCode.JWT_PARSE_ERROR.getCode());
        }
    }
}
