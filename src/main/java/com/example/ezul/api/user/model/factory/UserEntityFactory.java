package com.example.ezul.api.user.model.factory;

import com.example.ezul.api.user.model.domain.User;
import com.example.ezul.api.user.model.entity.UserEntity;
import com.example.ezul.base.model.domain.Administrator;
import com.example.ezul.base.model.domain.BaseAudit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntityFactory {

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getProvider(),
                user.getProviderId(),
                user.getLoginId(),
                user.getPassword(),
                user.getNickname(),
                user.getProfileImage(),
                user.getEmail(),
                user.getCi(),
                user.getProvince(),
                user.getCity(),
                user.getDistrict(),
                user.isResidentVerified(),
                user.isEnabled(),
                user.isRemoved(),
                user.getLastLoginAt(),
                user.getAudit().getCreator().getId(),
                user.getAudit().getModifier().getId(),
                user.getAudit().getCreatedAt(),
                user.getAudit().getModifiedAt()
        );
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return new User(
                entity.getId(),
                entity.getProvider(),
                entity.getProviderId(),
                entity.getLoginId(),
                entity.getPassword(),
                entity.getNickname(),
                entity.getProfileImage(),
                entity.getEmail(),
                entity.getCi(),
                entity.getProvince(),
                entity.getCity(),
                entity.getDistrict(),
                entity.isResidentVerified(),
                entity.isEnabled(),
                entity.isRemoved(),
                entity.getLastLoginAt(),
                new BaseAudit(
                        new Administrator(entity.getCreatorId()),
                        new Administrator(entity.getModifierId()),
                        entity.getCreatedAt(),
                        entity.getModifiedAt()
                )
        );
    }
}
