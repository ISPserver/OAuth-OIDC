package com.example.ezul.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserResponse {

    @Schema(description = "사용자 아이디", requiredMode = REQUIRED)
    Long id;

    @Schema(description = "소셜 로그인 제공자", requiredMode = NOT_REQUIRED)
    String provider;

    @Schema(description = "제공자 식별자 아이디", requiredMode = NOT_REQUIRED)
    String providerId;

    @Schema(description = "로그인 아이디", requiredMode = NOT_REQUIRED)
    String loginId;

    @Schema(description = "비밀번호", requiredMode = NOT_REQUIRED)
    String password;

    @Schema(description = "닉네임", requiredMode = NOT_REQUIRED)
    String nickname;

    @Schema(description = "프로필 이미지 URL", requiredMode = NOT_REQUIRED)
    String profileImage;

    @Schema(description = "이메일", requiredMode = NOT_REQUIRED)
    String email;

    @Schema(description = "연계정보(CI)", requiredMode = REQUIRED)
    String ci;

    @Schema(description = "시/도", requiredMode = REQUIRED)
    String province;

    @Schema(description = "시/군/구", requiredMode = REQUIRED)
    String city;

    @Schema(description = "구/동", requiredMode = REQUIRED)
    String district;

    @Schema(description = "경기도민 인증 여부", requiredMode = REQUIRED)
    boolean residentVerified;

    @Schema(description = "활성화 여부", requiredMode = REQUIRED)
    boolean enabled;

    @Schema(description = "삭제 여부", requiredMode = REQUIRED)
    boolean removed;

    @Schema(description = "마지막 로그인 일시", requiredMode = NOT_REQUIRED)
    LocalDateTime lastLoginAt;

    @Schema(description = "생성 일시", requiredMode = REQUIRED)
    LocalDateTime createdAt;

    @Schema(description = "수정 일시", requiredMode = REQUIRED)
    LocalDateTime modifiedAt;
}
