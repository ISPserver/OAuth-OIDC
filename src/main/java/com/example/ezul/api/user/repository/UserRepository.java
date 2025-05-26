package com.example.ezul.api.user.repository;

import com.example.ezul.api.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByProviderAndProviderIdAndRemovedFalse(String provider, String providerId);
    Optional<UserEntity> findByLoginIdAndRemovedFalse(String id);
}
