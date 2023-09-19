package com.sparta.fishingload_backend.repository;

import com.sparta.fishingload_backend.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
     Optional<RefreshToken> findByUserId(String userId);

    boolean existsByToken(String refresh);
}
