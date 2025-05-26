package com.example.ezul.api.user.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Comment("아이디")
    Long id;

    @Column(length = 6)
    @Comment("소셜 로그인 제공자 (kakao, naver, apple)")
    String provider;

    @Column(length = 100)
    @Comment("제공자 식별자 아이디")
    String providerId;

    @Column(length = 30)
    @Comment("로그인 아이디")
    String loginId;

    @Column(length = 100)
    @Comment("비밀번호")
    String password;

    @Column(length = 50)
    @Comment("닉네임")
    String nickname;

    @Column(length = 200)
    @Comment("프로필 이미지 URL")
    String profileImage;

    @Column(length = 100, nullable = false)
    @Comment("이메일")
    String email;

    @Comment("연계정보(CI)")
    String ci;

    @Column(length = 30, nullable = false)
    @Comment("시/도")
    String province;

    @Column(length = 30, nullable = false)
    @Comment("시/군/구")
    String city;

    @Column(length = 30, nullable = false)
    @Comment("구/동")
    String district;

    @Column(nullable = false)
    @Comment("경기도민 인증 여부")
    boolean residentVerified;

    @Column(nullable = false)
    @Comment("활성화 여부")
    boolean enabled;

    @Column(nullable = false)
    @Comment("삭제 여부")
    boolean removed;

    @Column
    @Comment("마지막 로그인 일시")
    LocalDateTime lastLoginAt;

    @Column(nullable = false)
    @Comment("생성자 아이디")
    Long creatorId;

    @Column(nullable = false)
    @Comment("수정자 아이디")
    Long modifierId;

    @CreatedDate
    @Column(nullable = false)
    @Comment("생성 일시")
    LocalDateTime createdAt;

    @CreatedDate
    @Column(nullable = false)
    @Comment("수정 일시")
    LocalDateTime modifiedAt;
}
