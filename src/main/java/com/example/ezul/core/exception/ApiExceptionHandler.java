package com.example.ezul.core.exception;

import com.example.ezul.base.dto.ApiResult;
import com.example.ezul.base.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler {
    private final MessageSource messageSource;

    /**
     * 사용자 정의 예외 발생 시 에러코드 및 메시지 처리
     * 상태코드에 따른 응답
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleConstraintViolation(final ApiException ex, WebRequest request, Locale locale) {
        String code = Optional.ofNullable(ex.getCode()).orElse(ErrorCode.SYSTEM_ERROR.getCode());
        ErrorCode errorCode = Optional.ofNullable(ErrorCode.findByCode(code)).orElse(ErrorCode.SYSTEM_ERROR);
        String msg = Optional.ofNullable(ex.getMsg()).orElse(errorCode.getMsg());
        try {
            msg = messageSource.getMessage(msg, null, locale);
        } catch (Exception e) {
            log.warn("MessageSource getMessage failed for code: {}, locale: {}", errorCode.getMsg(), locale);
        }

        if(!ObjectUtils.isEmpty(ex.getMsgArgs())) {
            String[] msgArgs = ex.getMsgArgs();
            if(msg.indexOf("{") > -1) {
                for (int i = 0; i < msgArgs.length; i++) {
                    msg = msg.replace("{"+i+"}", msgArgs[i]);
                }
            }
        }
        Map<String, Object> errorInfo = this.errorHashMap(ex, request, null);
        ApiResult apiResult = new ApiResult(code, msg);
        apiResult.setData(errorInfo);
        //log.info("[Error] UserHandleException Code: {} , Msg: {}", apiResult.getCode(), apiResult.getMsg());
        return ResponseEntity.status(errorCode.getStatus()).body(apiResult);
    }


    /**
     * 공통 예외 정보 - 예외 정보들 포함하여 응답
     */
    private Map<String, Object> errorHashMap(final Exception ex, WebRequest request, String exceptionKey) {
        Map<String, Object> hashMap = new LinkedHashMap<>();

        String path = request.getDescription(false); // 요청 경로

        hashMap.put("path", path);
        hashMap.put("error", ex.getClass().getSimpleName());
        hashMap.put("time", LocalDateTime.now());
        hashMap.put("message", ex.getMessage());
        if (exceptionKey != null) {
            hashMap.put("exceptionKey", exceptionKey);
        }
        return hashMap;
    }
}
