package com.example.ezul.core.exception;

import com.example.ezul.base.dto.ApiResult;
import com.example.ezul.base.enums.ErrorCode;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ValidExceptionHandler {

    private final MessageSource messageSource;

    /**
     * valid 관련 Advice
     *
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException e, WebRequest request) {
        String validMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return this.validExceptionHandler(e, request, validMessage);
    }


    /**
     * valid 관련 Advice
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationExceptionandler(final ConstraintViolationException e, WebRequest request) {
        String validMessage = e.getMessage();
        if(validMessage.indexOf(":") > -1) {
            validMessage = validMessage.substring(validMessage.indexOf(":") + 1);
            validMessage = validMessage.trim();
        }
        return this.validExceptionHandler(e, request, validMessage);
    }


    /**
     * Valild 체크 (Get Method Dto)
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindExceptionHandler(final BindException e, WebRequest request) {
        String validMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return this.validExceptionHandler(e, request, validMessage);
    }


    /**
     * valid 공통 info
     *
     */
    private ResponseEntity validExceptionHandler(final Exception e, WebRequest request, String validMessage) {
        String[] validMessages = validMessage.split(":");
//        for (String string : validMessages) {
//            log.info("!!!!!!!!!!!: {}", string);
//        }
        String code;
        String errorMsgCode;
        String subMessage = "";
        String localeMessage;

        ErrorCode errorCode;
        if(validMessages.length > 1) {
            errorCode = ErrorCode.findByCode(validMessages[0].trim());
            subMessage = validMessages[1].trim();
        } else {
            errorCode = ErrorCode.findByCode(validMessage.trim());
        }

        if (errorCode == null) {
            errorCode = ErrorCode.BAD_REQUEST;
        }
        code = errorCode.getCode();
        errorMsgCode = errorCode.getMsg();
//        log.info("ffffffffffffffff: {}", errorCode.getMsg());
        Locale locale = request.getLocale();
        localeMessage = messageSource.getMessage(errorMsgCode, null, locale);
//        log.info(localeMessage);

        if(localeMessage.indexOf("{") > -1) {
            String[] arrSubMessage = subMessage.split(",");
            for (int i = 0; i < arrSubMessage.length; i++) {
                localeMessage = localeMessage.replace("{"+i+"}", arrSubMessage[i]);
            }
        } else {
            subMessage = subMessage != "" ? "(" + subMessage + ")" : "";
            localeMessage += subMessage;
        }
        ApiResult apiResult = new ApiResult(code, localeMessage);

        return ResponseEntity.status(errorCode.getStatus()).body(apiResult);
    }

}
