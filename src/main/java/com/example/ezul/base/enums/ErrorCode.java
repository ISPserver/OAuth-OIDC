package com.example.ezul.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.EnumSet;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    // Error Default
    SYSTEM_ERROR                ("A0001", HttpStatus.OK, "error.A0001"),
    SESSION_EXPIRED             ("A0002", HttpStatus.OK, "error.A0002"),
    BAD_REQUEST                 ("A0003", HttpStatus.OK, "error.A0003"),

    SYSTEM_ERROR_ADM            ("A1001", HttpStatus.OK, "error.A1001"),
    NOT_SUPPORTED               ("A1002", HttpStatus.OK, "error.A1002"),

    REQUIRED_VALUE              ("A2001", HttpStatus.OK, "error.A2001"),
    NOT_ENOUGH_CHARACTERS_SIZE  ("A2002", HttpStatus.OK, "error.A2002"),

    INVALID_JWT_TOKEN           ("A3001", HttpStatus.OK, "error.A3001"),
    EXPIRED_JWT_TOKEN           ("A3002", HttpStatus.OK, "error.A3002"),
    UNSUPPORTED_JWT_TOKEN       ("A3003", HttpStatus.OK, "error.A3003"),
    EMPTY_JWT_TOKEN             ("A3004", HttpStatus.OK, "error.A3004"),
    JWT_PARSE_ERROR             ("A3005", HttpStatus.OK, "error.A3005"),
    OIDC_PUBLIC_KEY_NOT_MATCHED ("A3006", HttpStatus.OK, "error.A3006"),
    OIDC_PUBLIC_KEY_EMPTY       ("A3007", HttpStatus.OK, "error.A3007"),
    OIDC_PUBLIC_KEY_BUILD_FAIL  ("A3008", HttpStatus.OK, "error.A3008"),

    USER_NOT_FOUND              ("A3009", HttpStatus.OK, "error.A3009"),
    INVALID_CREDENTIALS         ("A3010", HttpStatus.OK, "error.A3010"),
    USER_DISABLED               ("A3011", HttpStatus.OK, "error.A3011"),
    ;

    private String code;
    private HttpStatus status;
    private String msg;

    public static ErrorCode findByCode(final String code) {
        if(!StringUtils.hasText(code)) return null;
        return EnumSet.allOf(ErrorCode.class).stream()
                .filter(e -> e.getCode().equals(code)).findAny().orElse(null);
    }

}
