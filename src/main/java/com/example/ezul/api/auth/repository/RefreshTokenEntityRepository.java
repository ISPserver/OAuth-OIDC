package com.example.ezul.api.auth.repository;

import com.example.ezul.api.auth.model.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenEntityRepository extends JpaRepository<RefreshTokenEntity, String> {
}
