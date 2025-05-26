package com.example.ezul.api.user.service;

import com.example.ezul.api.user.model.domain.User;
import com.example.ezul.api.user.model.entity.UserEntity;
import com.example.ezul.api.user.model.factory.UserEntityFactory;
import com.example.ezul.api.user.repository.UserRepository;
import com.example.ezul.base.enums.ErrorCode;
import com.example.ezul.core.exception.ApiException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserByProvider(String provider, String providerId) {
        UserEntity entity = userRepository.findByProviderAndProviderIdAndRemovedFalse(provider, providerId).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND.getCode()));
        return UserEntityFactory.toDomain(entity);
    }

    @Transactional(readOnly = true)
    public User getUser(String loginId) {
        UserEntity entity = userRepository.findByLoginIdAndRemovedFalse(loginId).orElseThrow(null);
        return UserEntityFactory.toDomain(entity);
    }

    public void save(User user) {
        userRepository.save(UserEntityFactory.toEntity(user));
    }
}
