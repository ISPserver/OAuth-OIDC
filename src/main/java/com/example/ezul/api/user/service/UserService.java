package com.example.ezul.api.user.service;

import com.example.ezul.api.auth.model.entity.UserEntity;
import com.example.ezul.api.user.repository.UserRepository;
import com.example.ezul.base.enums.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

     UserRepository userRepository;
     PasswordEncoder passwordEncoder;

     public UserResponse getUserByProvider(String provider, String providerId) {
         UserEntity entity = userRepository.findByProviderAndProviderId(provider, providerId)
                 .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST.getCode())
         return new UserResponse(user);
     }
}
