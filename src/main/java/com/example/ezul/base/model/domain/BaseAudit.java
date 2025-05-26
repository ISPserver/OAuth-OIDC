package com.example.ezul.base.model.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BaseAudit {

    Administrator creator;

    Administrator modifier;

    LocalDateTime createdAt;

    LocalDateTime modifiedAt;

    public BaseAudit(Long creatorId) {
        this.creator = new Administrator(creatorId);
        this.modifier = new Administrator(creatorId);
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void update(Long modifierId) {
        this.modifier = new Administrator(modifierId);
        this.modifiedAt = LocalDateTime.now();
    }
}
