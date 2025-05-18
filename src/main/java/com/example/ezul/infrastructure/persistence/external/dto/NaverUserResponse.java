package com.example.ezul.infrastructure.persistence.external.dto;

import lombok.Data;

@Data
public class NaverUserResponse {

    private NaverUser response;

    @Data
    public static class NaverUser {
        private String id;
        private String email;
        private String name;
        // TODO: 성별, 생일 등
    }
}