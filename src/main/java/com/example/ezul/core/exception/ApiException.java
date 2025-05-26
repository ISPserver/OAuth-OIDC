package com.example.ezul.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private String code;
    private String msg;
    private String[] msgArgs;

    public ApiException(final String code) {
        this.code = code;
    }

    public ApiException(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiException(final String code, final String[] msgArgs) {
        this.code = code;
        this.msgArgs = msgArgs;
    }
}
