package com.sparta.fishingload_backend.repository;

import com.sparta.fishingload_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserid(String userid);

    Optional<User> findByUseridAndUserUseTrue(String username);
}
